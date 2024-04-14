package com.pawan.DAO;
import com.pawan.model.Supplier;
import java.util.List;
public interface SupplierDAO {
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(int id);
    void addSupplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(int id);
    List<Supplier> getSuppliersSortedByName();
}
