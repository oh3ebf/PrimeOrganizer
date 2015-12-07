/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh3ebf.demo.service;

import javax.ejb.Stateless;
import oh3ebf.demo.model.BookMarkTreeNode;


@Stateless
public class BookMarkService extends DataAccessService<BookMarkTreeNode> {
    public BookMarkService() {
        super(BookMarkTreeNode.class);
    }
    
}
