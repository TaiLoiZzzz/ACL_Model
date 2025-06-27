package vn.tayjava.services.lmpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.tayjava.model.Role;
import vn.tayjava.repository.RoleRepository;
import vn.tayjava.services.RoleService;

import java.util.List;
@Service
@AllArgsConstructor
public class RoleServicelmpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getRolesByUserId(Long userId) {
      return  roleRepository.GetRoleByUserId(userId);

    }
}
