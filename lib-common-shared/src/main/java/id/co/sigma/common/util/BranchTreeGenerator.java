package id.co.sigma.common.util;

import id.co.sigma.common.security.domain.Branch;

import java.util.List;

/**
 * Tulis keterangan class di sini
 * @author <a href="mailto:gusti.darmawan@sigma.co.id">Eka Darmawan</a>
 */
public class BranchTreeGenerator {

	 private Branch root;
     private List<Branch> tree;

     public BranchTreeGenerator(Branch root, List<Branch> tree) {
         this.root = root;
         this.tree = tree;
     }

     public void createTree() {
         tree.add(root);
         for (Branch child : root.getChildren()) {
             createNode(child, tree);
         }
     }

     public void createNode(Branch node, List<Branch> tree) {
         tree.add(node);
         if (node.getChildren() != null) {
             for (Branch child : node.getChildren()) {
                 createNode(child, tree);
             }
         }
     }
}
