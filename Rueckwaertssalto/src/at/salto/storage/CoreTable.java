package at.salto.storage;

/**
 * DAS IST EIN PFUSCH
 * 
 * @author FOCK
 *
 */
public class CoreTable implements MetaDatenDecorator {
	MetadatenObject object;
	StringBuilder table;

	/**
	 * @param object
	 */
	public CoreTable(MetadatenObject object) {
		this.object = object;
		this.table.append(object.getTableName());
	}

	/**
	 * @return
	 */
	public StringBuilder getBuilder() {
		return this.table;
	}

	@Override
	public void decorateIt() {

	}

}
