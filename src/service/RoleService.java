package service;

import data.model.Medicine;
import data.model.Role;
import data.model.enums.RoleType;
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
