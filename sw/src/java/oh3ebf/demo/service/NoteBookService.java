
package oh3ebf.demo.service;

import javax.ejb.Stateless;
import oh3ebf.demo.model.NoteBookItem;

@Stateless
public class NoteBookService extends DataAccessService<NoteBookItem> {
    public NoteBookService() {
        super(NoteBookItem.class);
    }
}
