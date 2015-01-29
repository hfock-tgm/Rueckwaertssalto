package at.salto.storage;

/**
 *  DAS IST EIN PFUSCH
 * @author FOCK
 *
 */
public class Attribute implements MetaDatenDecorator{
	MetadatenObject object;
	StringBuilder builder;
	
	/**
	 * @param object
	 * @param builder
	 */
	public Attribute(MetadatenObject object, StringBuilder builder) {
		this.object = object;
		this.builder = builder;
	}
	
	@Override
	public void decorateIt() {
		this.builder.append(object.getColumns());	
	}

	@Override
	public StringBuilder getBuilder() {
		return builder;
	}
	
	@Override
	public String toString(){
		return this.builder.toString();
	}

}
