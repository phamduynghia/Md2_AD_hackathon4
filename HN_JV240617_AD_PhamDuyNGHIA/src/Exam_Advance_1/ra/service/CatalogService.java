package Exam_Advance_1.ra.service;
import Exam_Advance_1.ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IGenericService<Catalog, Integer> {

    private final List<Catalog> listCatalog = new ArrayList<>();

    @Override
    public List<Catalog> getAll() {
        return listCatalog;
    }

    @Override
    public void save(Catalog catalog) {
        if (findById(catalog.getCatalogId()) == null) {
            listCatalog.add(catalog);
        } else {
            listCatalog.set(listCatalog.indexOf(findById(catalog.getCatalogId())), catalog);
        }
    }

    @Override
    public Catalog findById(Integer integer) {
        for (Catalog c : listCatalog) {
            if (c.getCatalogId() == integer) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer integer) {
        if (findById(integer) != null) {
            listCatalog.remove(findById(integer));
        } else {
            System.err.println("Không có mục lục này!.");
        }
    }

    public int getNewCatalogId() {
        int maxId = 0;
        for (Catalog c : listCatalog) {
            if (c.getCatalogId() > maxId) {
                maxId = c.getCatalogId();
            }
        }
        return maxId + 1;
    }

}