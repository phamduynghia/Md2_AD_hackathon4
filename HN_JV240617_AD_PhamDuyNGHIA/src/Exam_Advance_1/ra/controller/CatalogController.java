package Exam_Advance_1.ra.controller;

import Exam_Advance_1.ra.model.Catalog;
import Exam_Advance_1.ra.service.CatalogService;

import java.util.List;

public class CatalogController implements IGenericController<Catalog, Integer> {
    private CatalogService catalogService = new CatalogService();

    @Override
    public List<Catalog> getAll() {
        return catalogService.getAll();
    }

    @Override
    public void save(Catalog catalog) {
        catalogService.save(catalog);
    }

    @Override
    public Catalog findById(Integer integer) {
        return catalogService.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        catalogService.delete(integer);
    }

    public int getNewId() {
        return catalogService.getNewCatalogId();
    }
}