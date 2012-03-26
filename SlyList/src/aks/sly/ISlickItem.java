package aks.sly;

public interface ISlickItem {

	public String getHeading1();

	public String getHeading2();

	public long getItemId();

	public void storeVal(int current);

	public int getCachedVal();

}
