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
import vn.Access_Control_List.controller.Request.CreateRoleRequest;
import vn.Access_Control_List.controller.Response.RoleResponse;
import vn.Access_Control_List.services.RoleService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Tag(name = "Role Management", description = "Role CRUD operations")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @Operation(summary = "Get all roles", description = "Retrieve paginated list of all roles")
    public ResponseEntity<Page<RoleResponse>> getAllRoles(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<RoleResponse> roles = roleService.getAllRoles(pageable);
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active roles", description = "Retrieve list of all active roles")
    public ResponseEntity<List<RoleResponse>> getAllActiveRoles() {
        List<RoleResponse> roles = roleService.getAllActiveRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role by ID", description = "Retrieve a specific role by their ID")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Integer id) {
        RoleResponse role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping
    @Operation(summary = "Create new role", description = "Create a new role with specified permissions")
    public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody CreateRoleRequest request) {
        RoleResponse role = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update role", description = "Update an existing role")
    public ResponseEntity<RoleResponse> updateRole(
            @PathVariable Integer id,
            @Valid @RequestBody CreateRoleRequest request
    ) {
        RoleResponse role = roleService.updateRole(id, request);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete role", description = "Delete a role by ID")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/permissions")
    @Operation(summary = "Assign permissions to role", description = "Assign permissions to a specific role")
    public ResponseEntity<RoleResponse> assignPermissions(
            @PathVariable Integer id,
            @RequestBody Set<Integer> permissionIds
    ) {
        RoleResponse role = roleService.assignPermissions(id, permissionIds);
        return ResponseEntity.ok(role);
    }
} 