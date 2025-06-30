package vn.Access_Control_List.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.Access_Control_List.controller.Request.CreateRoleRequest;
import vn.Access_Control_List.controller.Response.PermissionResponse;
import vn.Access_Control_List.controller.Response.RoleResponse;
import vn.Access_Control_List.model.PermissionEntity;
import vn.Access_Control_List.model.RoleEntity;
import vn.Access_Control_List.model.RoleHasPermission;
import vn.Access_Control_List.repository.PermissionRepository;
import vn.Access_Control_List.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public Page<RoleResponse> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable)
                .map(this::convertToRoleResponse);
    }

    public List<RoleResponse> getAllActiveRoles() {
        return roleRepository.findByIsActiveTrue()
                .stream()
                .map(this::convertToRoleResponse)
                .collect(Collectors.toList());
    }

    public RoleResponse getRoleById(Integer id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return convertToRoleResponse(role);
    }

    public RoleResponse createRole(CreateRoleRequest request) {
        // Check if role name already exists
        if (roleRepository.existsByName(request.getName())) {
            throw new RuntimeException("Role name is already taken");
        }

        // Create new role
        RoleEntity role = RoleEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .isActive(request.getIsActive())
                .build();

        role = roleRepository.save(role);

        // Assign permissions if provided
        if (request.getPermissionIds() != null && !request.getPermissionIds().isEmpty()) {
            assignPermissionsToRole(role, request.getPermissionIds());
        }

        return convertToRoleResponse(role);
    }

    public RoleResponse updateRole(Integer id, CreateRoleRequest request) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        // Check if role name already exists (excluding current role)
        if (!role.getName().equals(request.getName()) && 
            roleRepository.existsByName(request.getName())) {
            throw new RuntimeException("Role name is already taken");
        }

        // Update role fields
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setIsActive(request.getIsActive());

        // Update permissions
        if (request.getPermissionIds() != null) {
            // Clear existing permissions
            role.getPermissions().clear();
            roleRepository.save(role);
            
            // Assign new permissions
            assignPermissionsToRole(role, request.getPermissionIds());
        }

        role = roleRepository.save(role);
        return convertToRoleResponse(role);
    }

    public void deleteRole(Integer id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(role);
    }

    public RoleResponse assignPermissions(Integer roleId, Set<Integer> permissionIds) {
        RoleEntity role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        
        assignPermissionsToRole(role, permissionIds);
        return convertToRoleResponse(role);
    }

    private void assignPermissionsToRole(RoleEntity role, Set<Integer> permissionIds) {
        List<PermissionEntity> permissions = permissionRepository.findAllById(permissionIds);
        
        if (permissions.size() != permissionIds.size()) {
            throw new RuntimeException("One or more permissions not found");
        }

        Set<RoleHasPermission> rolePermissions = permissions.stream()
                .map(permission -> RoleHasPermission.builder()
                        .role(role)
                        .permission(permission)
                        .build())
                .collect(Collectors.toSet());

        role.setPermissions(rolePermissions);
    }

    private RoleResponse convertToRoleResponse(RoleEntity role) {
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
    }
} 