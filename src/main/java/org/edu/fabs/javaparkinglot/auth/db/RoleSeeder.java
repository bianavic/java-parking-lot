//package org.edu.fabs.javaparkinglot.auth.db;
//
//import org.edu.fabs.javaparkinglot.auth.entity.Role;
//import org.edu.fabs.javaparkinglot.auth.entity.RoleEnum;
//import org.edu.fabs.javaparkinglot.auth.repository.RoleRepository;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//
//import java.util.Arrays;
//import java.util.Map;
//import java.util.Optional;
//
//public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
//
//    private final RoleRepository roleRepository;
//
//    public RoleSeeder(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        this.loadRoles();
//    }
//
//    private void loadRoles() {
//        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN };
//        Map<RoleEnum, String> roleDescriptionMap = Map.of(
//                RoleEnum.USER, "Default user role",
//                RoleEnum.ADMIN, "Administrator role");
//
//        Arrays.stream(roleNames).forEach((roleName) -> {
//
//            Optional<Role> optionalRole = roleRepository.findByName(roleName);
//
//            optionalRole.ifPresentOrElse(System.out::println, () -> {
//                Role roleToCreate = new Role();
//                roleToCreate.setName(roleName);
//                roleToCreate.setDescription(roleDescriptionMap.get(roleName));
//
//                roleRepository.save(roleToCreate);
//            });
//        });
//    }
//
//}
