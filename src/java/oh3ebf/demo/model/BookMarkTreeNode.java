
package oh3ebf.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.primefaces.model.TreeNode;

@Entity
@NamedQueries({
    @NamedQuery(name = BookMarkTreeNode.ALL, query = "SELECT node FROM BookMarkTreeNode node LEFT JOIN fetch node.children WHERE node.parent=NULL"),
    @NamedQuery(name = BookMarkTreeNode.CHILDREN, query = "SELECT node.children FROM BookMarkTreeNode node WHERE node.parent.id=:parentID")
})
public class BookMarkTreeNode extends BaseEntity implements TreeNode, Serializable {
    // TreeNode data
    public static final String DEFAULT_TYPE = "default";
    public final static String ALL = "BookMarkTreeNode.populateTree";
    public final static String CHILDREN = "BookMarkTreeNode.populateDatatable";

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<BookMarkTreeNode> children;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private BookMarkTreeNode parent;

    private String type;
    @Transient
    private boolean expanded = false;
    @Transient
    private boolean selected = false;
    @Transient
    private String rowKey;
    @Transient
    private boolean selectable = true;
    @Transient
    private boolean partialSelectable = false;
    

    // user data
    private String name = "";
    private String url = "";
    private String description = "";
    
    public BookMarkTreeNode() {
        this.type = DEFAULT_TYPE;
        children = new ArrayList<>();
    }

    public BookMarkTreeNode(TreeNode parent) {
        this.type = DEFAULT_TYPE;
        
        children = new ArrayList<>();
        this.parent = (BookMarkTreeNode)parent;
        
        if (this.parent != null) {
            this.parent.getChildren().add(this);
        }
    }

    public BookMarkTreeNode(String type, TreeNode parent) {
        this.type = type;

        children = new ArrayList<>();
        this.parent = (BookMarkTreeNode)parent;
        
        if (this.parent != null) {
            this.parent.getChildren().add(this);
        }
    }

    @Override
    public Long getId() {
        return super.getId();
    }
    
    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public List<TreeNode> getChildren() {
        List<TreeNode> tmp = new ArrayList<>();
        tmp.addAll(children);
        return tmp;
    }

    public List<BookMarkTreeNode> getBookMarkChildren() {        
        return children;
    }
    
    public void setChildren(List<BookMarkTreeNode> children) {
        this.children = children;
    }

    @Override
    public BookMarkTreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = (BookMarkTreeNode)parent;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;

        if (parent != null) {
            parent.setExpanded(expanded);
        }
    }

    @Override
    public boolean isSelected() {
        return this.selected;
    }

    @Override
    public void setSelected(boolean value) {
        this.selected = value;
    }

    public void addChild(BookMarkTreeNode treeNode) {
        treeNode.setParent(this);
        children.add(treeNode);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public boolean isLeaf() {
        if (children == null) {
            return true;
        }

        return children.isEmpty();
    }

    @Override
    public boolean isSelectable() {
        // enable selection if childs are available
        /*if(children == null) {
            return false;
        }*/
        
        return selectable;
    }

    @Override
    public void setSelectable(boolean bln) {
        this.selectable = bln;
    }

    @Override
    public boolean isPartialSelected() {
        return this.partialSelectable;
    }

    @Override
    public void setPartialSelected(boolean bln) {
        this.partialSelectable = bln;
    }

    @Override
    public void setRowKey(String string) {
        this.rowKey = string;
        System.out.println("set row key: " + this.rowKey);
    }

    @Override
    public String getRowKey() {
        System.out.println("get row key: " + this.rowKey);
        return rowKey;
    }

    @Override
    public Object getData() {
        return name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
