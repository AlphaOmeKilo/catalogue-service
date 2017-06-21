package com.oracle.demo.controller;

import com.oracle.demo.model.CatalogueModel;
import com.oracle.demo.persistance.CatalogueOracleDBAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CatalogueServiceController {

    @Autowired
    CatalogueOracleDBAdapter dbAdapter;

    @RequestMapping(value = "/{id}", method = GET)
    public CatalogueModel getCatalogueEntry(@PathVariable int id) throws Exception {
           return dbAdapter.getCatalogueEntry(id);
    }

    @RequestMapping(value = "/", method = GET)
    public List<CatalogueModel> getCatalogueEntries() throws Exception {
        return dbAdapter.getCatalogueEntries();
    }

    @RequestMapping(value = "/", method = POST)
    public void addCatalogueEntry(@RequestBody CatalogueModel model) throws Exception {
        dbAdapter.addCatalogueEntry(model);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteCatalogueEntry(@PathVariable String id) throws Exception {
        dbAdapter.deleteCatalogueEntry(id);
    }

}
