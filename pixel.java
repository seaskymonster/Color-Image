public class pixel{
	public pixelofcolor red;
	public pixelofcolor green;
	public pixelofcolor blue;
	//short rgb;
	short energy;
	public pixel(){
		red=new pixelofcolor();
		green=new pixelofcolor();
		blue=new pixelofcolor();
	}
	
	
	/*public long energy(){
		long energy=(red.gx)^2+(red.gy)^2+(green.gx)^2+(green.gy)^2+(blue.gx)^2+(blue.gy)^2;
		return energy;
	}*/
}