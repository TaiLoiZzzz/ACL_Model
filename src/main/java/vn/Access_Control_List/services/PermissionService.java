package vn.Access_Control_List.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.Access_Control_List.controller.Request.CreatePermissionRequest;
import vn.Access_Control_List.controller.Response.PermissionResponse;
import vn.Access_Control_List.model.PermissionEntity;
import vn.Access_Control_List.repository.PermissionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public Page<PermissionResponse> getAllPermissions(Pageable pageable) {
        return permissionRepository.findAll(pageable)
                .map(this::convertToPermissionResponse);
    }

    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(this::convertToPermissionResponse)
                .collect(Collectors.toList());
    }

    public PermissionResponse getPermissionById(Integer id) {
        PermissionEntity permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        return convertToPermissionResponse(permission);
    }

    public List<PermissionResponse> getPermissionsByResource(String resource) {
        return permissionRepository.findByResource(resource)
                .stream()
                .map(this::convertToPermissionResponse)
                .collect(Collectors.toList());
    }

    public PermissionResponse createPermission(CreatePermissionRequest request) {
        // Check if permission name already exists
        if (permissionRepository.existsByName(request.getName())) {
            throw new RuntimeException("Permission name is already taken");
        }

        // Check if resource-action combination already exists
        if (permissionRepository.existsByResourceAndAction(request.getResource(), request.getAction())) {
            throw new RuntimeException("Permission with this resource and action already exists");
        }

        // Create new permission
        PermissionEntity permission = PermissionEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .resource(request.getResource())
                .action(request.getAction())
                .build();

        permission = permissionRepository.save(permission);
        return convertToPermissionResponse(permission);
    }

    public PermissionResponse updatePermission(Integer id, CreatePermissionRequest request) {
        PermissionEntity permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));

        // Check if permission name already exists (excluding current permission)
        if (!permission.getName().equals(request.getName()) && 
            permissionRepository.existsByName(request.getName())) {
            throw new RuntimeException("Permission name is already taken");
        }

        // Check if resource-action combination already exists (excluding current permission)
        if ((!permission.getResource().equals(request.getResource()) || 
             !permission.getAction().equals(request.getAction())) &&
            permissionRepository.existsByResourceAndAction(request.getResource(), request.getAction())) {
            throw new RuntimeException("Permission with this resource and action already exists");
        }

        // Update permission fields
        permission.setName(request.getName());
        permission.setDescription(request.getDescription());
        permission.setResource(request.getResource());
        permission.setAction(request.getAction());

        permission = permissionRepository.save(permission);
        return convertToPermissionResponse(permission);
    }

    public void deletePermission(Integer id) {
        PermissionEntity permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        permissionRepository.delete(permission);
    }

    private PermissionResponse convertToPermissionResponse(PermissionEntity permission) {
        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .resource(permission.getResource())
                .action(permission.getAction())
                .createdAt(permission.getCreatedAt())
                .updatedAt(permission.getUpdatedAt())
                .build();
    }
} 