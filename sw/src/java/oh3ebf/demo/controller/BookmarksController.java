/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh3ebf.demo.controller;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import oh3ebf.demo.service.BookMarkService;
import oh3ebf.demo.model.BookMarkTreeNode;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

//@Named(value = "bookmarksController")
@ManagedBean
@SessionScoped
public class BookmarksController implements Serializable {

    private BookMarkTreeNode root;
    private BookMarkTreeNode selectedTreeNode;
    private BookMarkTreeNode selectedBookMark;
    private BookMarkTreeNode currentBookMark;
    List<BookMarkRow> d;

    @EJB
    BookMarkService service;

    /**
     * Creates a new instance of BookmarksController
     */
    public BookmarksController() {
    }

    /**
     * Function populates tree
     *
     */
    @PostConstruct
    public void init() {
        //selectedBookMark = new BookMarkRow();
        selectedTreeNode = new BookMarkTreeNode();
        currentBookMark = new BookMarkTreeNode();
        d = new ArrayList<>();
        //d.add(new BookMarkRow(0, "testi", "juu"));
        //d.add(new BookMarkRow(1, "testi 2", "juu juu"));

        List<BookMarkTreeNode> a = service.findWithNamedQuery(BookMarkTreeNode.ALL);
        if (!a.isEmpty()) {
            root = a.get(0);
        } else {
            root = new BookMarkTreeNode(null);
            root.setName("Bookmarks");
        }

        //test();
    }

    private void test() {
        root = new BookMarkTreeNode(null);
        root.setName("Bookmarks");

        BookMarkTreeNode a = new BookMarkTreeNode();
        a.setUrl("http://foo.bar.com");
        a.setName("foo bar");

        root.addChild(a);

        BookMarkTreeNode n = new BookMarkTreeNode();
        n.setUrl("http://foo.bar.com");
        n.setName("foo bar");

        a.addChild(n);
        service.create(root);
    }

    /**
     * Function returns root node of tree
     *
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Function returns currently selected tree node
     *
     * @return
     */
    public BookMarkTreeNode getSelectedTreeNode() {
        return this.selectedTreeNode;
    }

    /**
     * Function sets new current selection of tree
     *
     * @param node
     */
    public void setSelectedTreeNode(BookMarkTreeNode node) {
        this.selectedTreeNode = node;

        //d.clear();
        //int i = 0;
        /*for (BookMarkTreeNode b : this.selectedTreeNode.getBookMarkChildren()) {
         if (b.getChildCount() == 0) {
         d.add(new BookMarkRow(i++, b.getName(), b.getUrl()));
         }
         }*/
        /*
        for (int i = 0; i < 5; i++) {
            d.add(new BookMarkRow(i, "foo" + i, "bar-" + i));
        }
        */
    }

    /**
     * Function return those bookmark items which don't have childs
     *
     * @return List of bookmarks
     */
    public List<BookMarkTreeNode> getBookMarkList() {
        try {

            HashMap param = new HashMap();
            param.put("parentID", (selectedTreeNode.getParent()).getId());
            
            List l = service.findWithNamedQuery(BookMarkTreeNode.CHILDREN, param);
            
            // implement better handling for null value
            if(l.contains(null)) {
                System.out.println("null found");
                l.clear();
            }
            
            return l;
        } catch (Exception ex) {
            return new ArrayList<>();
        }

    }

    /*
     public void setBookMarkList(List<BookMarkTreeNode> bookMarks) {
     this.d = bookMarks;
     }
     */
    /**
     * @return the selectedBookMark
     */
    public BookMarkTreeNode getSelectedBookMark() {
        return this.selectedBookMark;
    }

    /**
     * @param bookMark the selectedBookMark to set
     */
    public void setSelectedBookMark(BookMarkTreeNode bookMark) {

        this.selectedBookMark = bookMark;
        //this.currentBookMark = bookMark;

        //this.currentBookMark = new BookMarkTreeNode();
        //this.currentBookMark.setUrl("diipa");
    }

    public void onRowSelect(SelectEvent event) {
        System.out.println("event");
    }

    /**
     * @return the currentBookMark
     */
    public BookMarkTreeNode getCurrentBookMark() {
        return this.currentBookMark;
    }

    /**
     * @param bookMark the currentBookMark to set
     */
    public void setCurrentBookMark(BookMarkTreeNode bookMark) {
        this.currentBookMark = bookMark;
    }
}
