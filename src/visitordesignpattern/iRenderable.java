package visitordesignpattern;

public interface iRenderable {
	public void accept(iRenderVisitor visitor);
}
