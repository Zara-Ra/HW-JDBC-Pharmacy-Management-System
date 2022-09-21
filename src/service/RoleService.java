package service;

import data.entity.Medicine;
import data.entity.Role;
import data.repository.MedicineDao;

import java.sql.SQLException;

public interface RoleService {
    Role signIn(String username, String password) throws SQLException;

    boolean signOut(Role role);

    Role signUp(Role newRole) throws SQLException;

    default Medicine findMedicine(String commercialName) throws SQLException {
        return MedicineDao.getInstance().findMedicineByCommercialName(commercialName);
    }
}
