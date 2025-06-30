package vn.Access_Control_List.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.Access_Control_List.controller.Request.CreatePermissionRequest;
import vn.Access_Control_List.controller.Response.PermissionResponse;
import vn.Access_Control_List.services.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@Tag(name = "Permission Management", description = "Permission CRUD operations")
@SecurityRequirement(name = "bearerAuth")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    @Operation(summary = "Get all permissions", description = "Retrieve paginated list of all permissions")
    @PreAuthorize("hasAuthority('READ_PERMISSIONS') or hasRole('ADMIN')")
    public ResponseEntity<Page<PermissionResponse>> getAllPermissions(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<PermissionResponse> permissions = permissionService.getAllPermissions(pageable);
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all permissions without pagination", description = "Retrieve list of all permissions")
    @PreAuthorize("hasAuthority('PERMISSIONS_READ') or hasRole('ADMIN')")
    public ResponseEntity<List<PermissionResponse>> getAllPermissionsWithoutPagination() {
        List<PermissionResponse> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get permission by ID", description = "Retrieve a specific permission by their ID")
    @PreAuthorize("hasAuthority('PERMISSIONS_READ') or hasRole('ADMIN')")
    public ResponseEntity<PermissionResponse> getPermissionById(@PathVariable Integer id) {
        PermissionResponse permission = permissionService.getPermissionById(id);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/resource/{resource}")
    @Operation(summary = "Get permissions by resource", description = "Retrieve permissions for a specific resource")
    @PreAuthorize("hasAuthority('PERMISSIONS_READ') or hasRole('ADMIN')")
    public ResponseEntity<List<PermissionResponse>> getPermissionsByResource(@PathVariable String resource) {
        List<PermissionResponse> permissions = permissionService.getPermissionsByResource(resource);
        return ResponseEntity.ok(permissions);
    }

    @PostMapping
    @Operation(summary = "Create new permission", description = "Create a new permission")
    @PreAuthorize("hasAuthority('PERMISSIONS_CREATE') or hasRole('ADMIN')")
    public ResponseEntity<PermissionResponse> createPermission(@Valid @RequestBody CreatePermissionRequest request) {
        PermissionResponse permission = permissionService.createPermission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(permission);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update permission", description = "Update an existing permission")
    @PreAuthorize("hasAuthority('PERMISSIONS_UPDATE') or hasRole('ADMIN')")
    public ResponseEntity<PermissionResponse> updatePermission(
            @PathVariable Integer id,
            @Valid @RequestBody CreatePermissionRequest request
    ) {
        PermissionResponse permission = permissionService.updatePermission(id, request);
        return ResponseEntity.ok(permission);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete permission", description = "Delete a permission by ID")
    @PreAuthorize("hasAuthority('PERMISSIONS_DELETE') or hasRole('ADMIN')")
    public ResponseEntity<Void> deletePermission(@PathVariable Integer id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
} 