/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh3ebf.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import oh3ebf.demo.model.NoteBookItem;
import oh3ebf.demo.service.NoteBookService;

/**
 *
 * @author kimkr
 */
//@Named(value = "notebookController")
@ManagedBean
//@Dependent
@SessionScoped
public class NotebookController implements Serializable {

    @EJB
    NoteBookService noteService;

    private List<String> notes = new ArrayList<>();
    private NoteBookItem currentItem;

    /**
     * Creates a new instance of NotebookController
     */
    public NotebookController() {
    }

    @PostConstruct
    public void init() {
        this.currentItem = new NoteBookItem();
    }

    public List<NoteBookItem> getNotes() {
        //return notes;
        return noteService.findWithNamedQuery(NoteBookItem.ALL);
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    /**
     * @return the selectedNote
     */
    public NoteBookItem getSelectedNote() {
        return this.currentItem;
    }

    /**
     * @param selectedNote the selectedNote to set
     */
    public void setSelectedNote(NoteBookItem selectedNote) {
        this.currentItem = selectedNote;
    }

    /**
     * @return the currentItem
     */
    public NoteBookItem getCurrentItem() {
        return this.currentItem;
    }

    /**
     * @param currentItem the currentItem to set
     */
    public void setCurrentItem(NoteBookItem currentItem) {
        this.currentItem = currentItem;
    }

    /**
     * Function clears editing variables
     *
     */
    public void addNewNoteBookItem() {
        this.currentItem = new NoteBookItem();
    }

    /**
     * Function stores new note or updates existing
     *
     */
    public void saveNoteBookItem() {
        // update date        
        this.currentItem.setUpdatedAt(new Date());

        // handle new note
        if (this.currentItem.getCreatedAt() == null) {
            // set create date
            this.currentItem.setCreatedAt(new Date());
            // save to database
            noteService.create(currentItem);
        } else {
            // update existing
            noteService.update(currentItem);
        }
        // clear edit component
        currentItem = new NoteBookItem();
    }
    
    /** Function removes note from data base
     * 
     */
    public void deleteNoteBookItem() {
        noteService.delete(this.currentItem.getId());
        this.currentItem = new NoteBookItem();
    }
}
