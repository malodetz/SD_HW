package ru.hse.fmcs.Parsing.ASTNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ASTNode implements Iterable<ASTNode> {
  protected List<ASTNode> children = new ArrayList<>();

  /**
   * Iterator for arbitrary tree. Firstly iterates over children
   * of node, then over node itself.
   */
  private class ASTNodeIterator implements Iterator<ASTNode> {
    private final ASTNode parent;
    private final Iterator<ASTNode> childrenListIterator = children.iterator();
    private Iterator<ASTNode> astChildrenIterator = null;
    private boolean finished = false;

    public ASTNodeIterator(final ASTNode parent) {
      this.parent = parent;
    }

    @Override
    public boolean hasNext() {
      return !finished;
    }

    @Override
    public ASTNode next() {
      if (finished) {
        return null;
      }

      if (astChildrenIterator == null) {
        if (childrenListIterator.hasNext()) {
          astChildrenIterator = childrenListIterator.next().iterator();
        } else {
          finished = true;
          return parent;
        }
      }

      if (astChildrenIterator.hasNext()) {
        ASTNode result = astChildrenIterator.next();
        if (!astChildrenIterator.hasNext()) {
          astChildrenIterator = null;
        }
        return result;
      }

      return null;
    }
  }

  @Override
  public Iterator<ASTNode> iterator() {
    return new ASTNodeIterator(this);
  }
}