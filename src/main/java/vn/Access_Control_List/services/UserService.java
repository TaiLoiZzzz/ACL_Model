package vn.Access_Control_List.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.Access_Control_List.controller.Request.CreateUserRequest;
import vn.Access_Control_List.controller.Response.PermissionResponse;
import vn.Access_Control_List.controller.Response.RoleResponse;
import vn.Access_Control_List.controller.Response.UserResponse;
import vn.Access_Control_List.model.RoleEntity;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.model.UserHasRoleEntity;
import vn.Access_Control_List.repository.RoleRepository;
import vn.Access_Control_List.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::convertToUserResponse);
    }
    public Optional<UserEntity> findUserByUserName(String userName) {
            return userRepository.findByUsername(userName);
    }

    public UserResponse getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToUserResponse(user);
    }

    public UserResponse createUser(CreateUserRequest request) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // Create new user
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .isActive(request.getIsActive())
                .build();

        user = userRepository.save(user);

        // Assign roles if provided
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            assignRolesToUser(user, request.getRoleIds());
        }

        return convertToUserResponse(user);
    }

    public UserResponse updateUser(Long id, CreateUserRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Check if username or email already exists (excluding current user)
        if (!user.getUsername().equals(request.getUsername()) && 
            userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        if (!user.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // Update user fields
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setIsActive(request.getIsActive());

        // Update password if provided
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Update roles
        if (request.getRoleIds() != null) {
            // Clear existing roles
            user.getRoles().clear();
            userRepository.save(user);
            
            // Assign new roles
            assignRolesToUser(user, request.getRoleIds());
        }

        user = userRepository.save(user);
        return convertToUserResponse(user);
    }

    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }

    public UserResponse assignRoles(Long userId, Set<Integer> roleIds) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        assignRolesToUser(user, roleIds);
        return convertToUserResponse(user);
    }

    private void assignRolesToUser(UserEntity user, Set<Integer> roleIds) {
        List<RoleEntity> roles = roleRepository.findAllById(roleIds);
        
        if (roles.size() != roleIds.size()) {
            throw new RuntimeException("One or more roles not found");
        }

        Set<UserHasRoleEntity> userRoles = roles.stream()
                .map(role -> UserHasRoleEntity.builder()
                        .user(user)
                        .role(role)
                        .build())
                .collect(Collectors.toSet());

        user.setRoles(userRoles);
    }

    private UserResponse convertToUserResponse(UserEntity user) {
        Set<RoleResponse> roleResponses = new HashSet<>();
        
        if (user.getRoles() != null) {
            roleResponses = user.getRoles().stream()
                    .map(userRole -> {
                        RoleEntity role = userRole.getRole();
                        Set<PermissionResponse> permissionResponses = new HashSet<>();
                        
                        if (role.getPermissions() != null) {
                            permissionResponses = role.getPermissions().stream()
                                    .map(rolePermission -> PermissionResponse.builder()
                                            .id(rolePermission.getPermission().getId())
                                            .name(rolePermission.getPermission().getName())
                                            .description(rolePermission.getPermission().getDescription())
                                            .resource(rolePermission.getPermission().getResource())
                                            .action(rolePermission.getPermission().getAction())
                                            .createdAt(rolePermission.getPermission().getCreatedAt())
                                            .updatedAt(rolePermission.getPermission().getUpdatedAt())
                                            .build())
                                    .collect(Collectors.toSet());
                        }
                        
                        return RoleResponse.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .description(role.getDescription())
                                .isActive(role.getIsActive())
                                .createdAt(role.getCreatedAt())
                                .updatedAt(role.getUpdatedAt())
                                .permissions(permissionResponses)
                                .build();
                    })
                    .collect(Collectors.toSet());
        }

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .roles(roleResponses)
                .build();
    }
}