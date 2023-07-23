public class Main {
    private class Node {
        public Node parent; 
        public Node left; 
        public Node right; 
        private boolean isLocked = false;

        public boolean lock() {
            if(isLocked)
                return false;

            if(isNotParentLock(this) || isNotChildrenLock(this)){
                isLocked = true;
                return true;
            }
            return false;
        }

        private boolean isNotParentLock(Node root) {
            if(root != null) {
                if(root.isLocked) {
                    return false;
                } else {
                    return isNotParentLock(root.parent);
                }
            }
            return true;
        }

        private boolean isNotChildrenLock(Node root) {
            if(root.left != null) {
                if (root.left.isLocked) {
                    return false;
                } else {
                    return isNotChildrenLock(root.left);
                }
            }

            if(root.right != null) {
                if(root.right.isLocked) {
                    return false;
                } else {
                    return isNotChildrenLock(root.right);
                }
            }

            return true;
        }

        public boolean unlock() {
            if(!isLocked)
                return false;

            if(isNotParentLock(this) || isNotChildrenLock(this)){
                isLocked = false;
                return true;
            }
            return false;
        }


    }

    
    public static void main(String[] args) {
        Main main = new Main();
        main.start();

    }

    public void start() {
        var root = new Node();
        var left = new Node();
        var right =  new Node();
        var parentRoot = new Node();

        root.left = left;
        root.right = right;
        root.parent = parentRoot;
        parentRoot.left = root;
        left.parent = root;
        right.parent = root;

        System.out.println(left.lock());
        System.out.println(parentRoot.lock());
        System.out.println(root.lock());
        System.out.println(left.unlock());
        System.out.println(root.lock());
        System.out.println(parentRoot.unlock());

    
    }


}