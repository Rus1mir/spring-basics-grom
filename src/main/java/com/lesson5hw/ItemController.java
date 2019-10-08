package com.lesson5hw;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    private ItemDao itemDao;

    @Autowired
    public ItemController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<String> save(@RequestBody Item item) {

        itemDao.save(item);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find")
    public ResponseEntity<String> findById(@RequestParam(value = "id") Long id) throws Exception {

        Item item = itemDao.findById(id);
        if (item == null)
            throw new NotFoundException("Item id: " + id + " was not found");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<String> update(@RequestBody Item item) throws Exception {

        if (itemDao.findById(item.getId()) == null)
            throw new NotFoundException("Item id: " + item.getId() + " was not found");

        itemDao.update(item);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity<String> delete(Long id) throws Exception {

        itemDao.delete(id);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> error(DataIntegrityViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> error(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> error(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
