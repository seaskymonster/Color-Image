/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */
import java.util.Iterator;

import list.*;
public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
  private DList run;
  private int height;
  private int width;
  private  int length;
 // private record[] record;
 // private int size;


  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
	  run=new DList();
	  this.height=height;
	  this.width=width;
	  record record=new record();
	  record.runlength=width*height;
	  run.insertFront(record);
	  length=1;
	 // this.size=height*width;
    // Your solution here.
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
	  this.width=width;
	  this.height=height;
	  
	  run=new DList();
	  for(int i=0;i<runLengths.length;i++){
		  record record=new record();
		  record.pixel.red.intensity=(short)red[i];
		  record.pixel.green.intensity=(short)green[i];
		  record.pixel.blue.intensity=(short)blue[i];
		  record.runlength=runLengths[i];
		  
		
		  run.insertBack(record);
		  length++;
	  }
	  //System.out.println("tostring"+ run.toString());
    // Your solution here.
  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
	  RunIterator i=new RunIterator((DListNode)(run.front()),length);
	 
    // Replace the following line with your solution.
    return i;
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
	  PixImage image=new PixImage(width,height);
	  
	  int a=0; int b=0;
	 for(RunIterator iter=iterator();iter.hasNext();){
		int[] pixel= iter.next();
		//b=b+(a+pixel[0])/width;
		//a=(a+pixel[0])%width;
		int counter=pixel[0];
		int flag=0;
		int a1=0; int b1=0;
		loop:
			
		for(int j=b;j<height;j++){
			for(int i=a;i<width;i++){
				
			//	System.out.println("ji=="+j+i+";pixel[1]=="+pixel[1]);
				image.setPixel(i, j, (short)pixel[1], (short)pixel[2], (short)pixel[3]);
				counter--;
			//	System.out.println("b1a1=="+b+""+a+"flag=="+flag);
				
			
			if((counter==0)&&(i!=width-1)){
				    
				    if(flag==1){
				    b=b1+(a1+pixel[0])/width;
				    a=(a1+pixel[0])%width;
				    }else{
					b=b+(a+pixel[0])/width;
					a=(a+pixel[0])%width;
				    }
					break loop;
				}
			else if((i==width-1)&&(counter!=0)){
				  if(flag==0){
		    	   flag++;
		    	   a1=a;
		    	   b1=b;
		    	   a=0;
		    	   b++;
				  }else{
					a=0;
					b++;
				  }
		    }
			else  if((i==width-1)&&(counter==0)){
		    	if(flag==1){
				    b=b1+(a1+pixel[0])/width;
				    a=(a1+pixel[0])%width;
				    }else{
					b=b+(a+pixel[0])/width;
					a=(a+pixel[0])%width;
				    }
					break loop;
		    }
			}
		}//this may have a big problem...
		
		
		
	 }
	 //image.helper(image);
    // Replace the following line with your solution.
    return image;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
	  String result="";
	  int[] a;
	 // System.out.println("good");
	  for(RunIterator iter=iterator();iter.hasNext();){
		   
		    a=iter.next();
		    //System.out.println(""+a[1]+a[0]);
	        result+=""+a[1]+""+a[0];
	        result+=",";
	      
	      
	    }
	  
	  
	  
	    return result ;
    // Replace the following line with your solution.
    
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
	  run=new DList();
	  this.width=image.getWidth();
	  this.height=image.getHeight();
	  
			  int count=1;
			  int i=0;
			  int j=0;
			  int a=1;
			  int b=0;
			  while(!((i==width-1)&&(j==height-1))){
				// System.out.println("ij=="+i+j+"ab=="+a+b);
				// System.out.println("length=="+length);
			  if((image.getRed(i,j)==image.getRed(a,b))&&(image.getGreen(i,j)==image.getGreen(a,b))&&(image.getBlue(i,j)==image.getBlue(a,b))){
				  b=b+(a+1)/width;
				  a=(a+1)%width;
				 
				  count++;
			  }else{
				    record record=new record();
			        record.pixel.red.intensity=(short)image.getRed(i, j);
			        record.pixel.green.intensity=(short)image.getGreen(i, j);
			        record.pixel.blue.intensity=(short)image.getBlue(i, j);
			        record.runlength=count;
			        run.insertBack(record);
			        ++length;
			        count=1;
			      
			 // System.out.println("ij=="+i+j+"ab=="+a+b);
				  
				  if((a==width-1)&&(b==height-1)){
					  record recordlast=new record();
					  recordlast.pixel.red.intensity=(short)image.getRed(a, b);
					  recordlast.pixel.green.intensity=(short)image.getGreen(a, b);
					  recordlast.pixel.blue.intensity=(short)image.getBlue(a, b);
					  recordlast.runlength=count;
					  run.insertBack(recordlast);
					  ++length;
				  }
				  
				    i=a;
			        j=b;
			        b=b+(a+1)/width;
			        a=(a+1)%width;
			        
			       // System.out.println("length=="+length);
			  }
			
	  }
    // Your solution here, but you should probably leave the following line
    // at the end.
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
    // Your solution here.
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
	  
	  int nth=y*width+x+1;
	  int lengths=0;
	  int previouslength=0;
	  DListNode node=(DListNode) run.front();
	  try{
		  while(node.next()!=null){
		   lengths+= ((record)(node.item())).runlength;
		   previouslength=lengths-((record)(node.item())).runlength;
		  
		  // System.out.println("previous=="+previouslength+"lengths=="+lengths+"nth=="+nth+"currentnode=="+(((record)(node.item())).pixel.red.intensity));
		  
		if(lengths>=nth){
			   
		  if((nth==previouslength+1)&&(nth==lengths)){
				  
			     if(((previouslength!=0)&&(red==(((record)(node.prev().item())).pixel.red.intensity))&&(green==(((record)(node.prev().item())).pixel.green.intensity))&&(blue==(((record)(node.prev().item())).pixel.blue.intensity)))&&
				      ((lengths!=width*height)&&(red==(((record)(node.next().item())).pixel.red.intensity))&&(green==(((record)(node.next().item())).pixel.green.intensity))&&(blue==(((record)(node.next().item())).pixel.blue.intensity))) )  {
				     // System.out.println(""+"okay");
				      ((record)(node.item())).pixel.red.intensity=red;
				      ((record)(node.item())).pixel.green.intensity=green;
				      ((record)(node.item())).pixel.blue.intensity=blue;
				      ((record)(node.item())).runlength = ((record)(node.item())).runlength+ ((record)(node.prev().item())).runlength+ ((record)(node.next().item())).runlength;
				      node.prev().remove();
				      node.next().remove();
				      length=length-2;
				      break;
			  
				  }else if((previouslength!=0)&&(red==(((record)(node.prev().item())).pixel.red.intensity))&&(green==(((record)(node.prev().item())).pixel.green.intensity))&&(blue==(((record)(node.prev().item())).pixel.blue.intensity))){
					   //System.out.println("okay2");
					   ((record)(node.prev().item())).runlength++; 
					   ((record)(node.item())).runlength--; 
					   if(((record)(node.item())).runlength==0){
						   node.remove();
						   length--;
					   }
					   break; 
				  }else if((lengths!=width*height)&&(red==(((record)(node.next().item())).pixel.red.intensity))&&(green==(((record)(node.next().item())).pixel.green.intensity))&&(blue==(((record)(node.next().item())).pixel.blue.intensity))){
					  // System.out.println("okay2");
					   ((record)(node.next().item())).runlength++; 
					   ((record)(node.item())).runlength--; 
					   if(((record)(node.item())).runlength==0){
						   node.remove();
						   length--;
					   }
					   break;
				  }else if((red==(((record)(node.item())).pixel.red.intensity))&&(green==(((record)(node.item())).pixel.green.intensity))&&(blue==(((record)(node.item())).pixel.blue.intensity))){
						   //System.out.println("okay1");
						  // ((record)(node.item())).runlength++; 
						   break;
						
				  }else{
				
					  ((record)(node.item())).pixel.red.intensity=red;
					  ((record)(node.item())).pixel.green.intensity=green;
					  ((record)(node.item())).pixel.blue.intensity=blue;
					   break;
				  }
				  
		       
	  }else if(nth==previouslength+1){
				   
				  // System.out.println("okay"+"red=="+red+"nodered=="+(((record)(node.item())).pixel.red.intensity));
				   if((red==(((record)(node.item())).pixel.red.intensity))&&(green==(((record)(node.item())).pixel.green.intensity))&&(blue==(((record)(node.item())).pixel.blue.intensity))){
					   //System.out.println("okay1");
					  // ((record)(node.item())).runlength++; 
					   break;
					}else if((previouslength!=0)&&(red==(((record)(node.prev().item())).pixel.red.intensity))&&(green==(((record)(node.prev().item())).pixel.green.intensity))&&(blue==(((record)(node.prev().item())).pixel.blue.intensity))){
					   //System.out.println("okay2");
					   ((record)(node.prev().item())).runlength++; 
					   ((record)(node.item())).runlength--; 
					   if(((record)(node.item())).runlength==0){
						   node.remove();
						   length--;
					   }
					   break;
			       }else{
					   //System.out.println("okay3");
					   record r1=new record();
					   r1.runlength=1;
					   r1.pixel.red.intensity=red;
					   r1.pixel.green.intensity=green;
					   r1.pixel.blue.intensity=blue;
					   node.insertBefore(r1); 
					   length++;
					   ((record)(node.item())).runlength--; 
					 
					   
					   if(((record)(node.item())).runlength==0){
						   node.remove();
						   length--;
					   }
					   
					   
					   break;
			   }
				   
	 }else if(nth==lengths){
				   //System.out.println("okay"+length);
				   if((red==(((record)(node.item())).pixel.red.intensity))&&(green==(((record)(node.item())).pixel.green.intensity))&&(blue==(((record)(node.item())).pixel.blue.intensity))){
					   System.out.println("okay1");
					   //((record)(node.item())).runlength++; 
					   break;
				   }else if((lengths!=width*height)&&(red==(((record)(node.next().item())).pixel.red.intensity))&&(green==(((record)(node.next().item())).pixel.green.intensity))&&(blue==(((record)(node.next().item())).pixel.blue.intensity))){
					  // System.out.println("okay2");
					   ((record)(node.next().item())).runlength++; 
					   ((record)(node.item())).runlength--; 
					   if(((record)(node.item())).runlength==0){
						   node.remove();
						   length--;
					   }
					   break;
				   }else{
					  // System.out.println("okay3");
					   record r1=new record();
					   r1.runlength=1;
					   r1.pixel.red.intensity=red;
					   r1.pixel.blue.intensity=blue;
					   r1.pixel.green.intensity=green;
					   node.insertAfter(r1); 
					   length++;
					   ((record)(node.item())).runlength--; 
					   if(((record)(node.item())).runlength==0){
						  length--;
					   }
					   break;
				   }
				   
	 }else{
				   if((red==(((record)(node.item())).pixel.red.intensity))&&(green==(((record)(node.item())).pixel.green.intensity))&&(blue==(((record)(node.item())).pixel.blue.intensity))){
					 //  ((record)(node.item())).runlength++;  
					   break;
					   
				   }else{
					  
					   record r1=new record();
					   r1.runlength=1;
					   r1.pixel.red.intensity=red;
					   r1.pixel.blue.intensity=blue;
					   r1.pixel.green.intensity=green;
					   node.insertAfter(r1);
					   length++;
					   record r2=new record();
					   r2.runlength=lengths-nth;
					   r2.pixel.red.intensity=((record)(node.item())).pixel.red.intensity;
					   r2.pixel.green.intensity=((record)(node.item())).pixel.green.intensity;
					   r2.pixel.blue.intensity=((record)(node.item())).pixel.blue.intensity;
					   length++;
					   if(lengths-nth!=0){
					      node.next().insertAfter(r2);
					   }
					   ((record)(node.item())).runlength-=(nth-previouslength);
					   if(((record)(node.item())).runlength==0){
						   node.remove();
						  length--;
					   }
					   break;
					   
				   }
			   }
		   } node=(DListNode)node.next();
		  }
	  }catch(InvalidNodeException e){
		  System.err.println("error");
	  }
    // Your solution here, but you should probably leave the following line
    //   at the end.
    check();
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
	  int[] rr = {0, 1, 2, 3, 4, 5};
	    int[] rg = {20, 18, 16, 14, 12, 10};
	    int[] rb = {42, 42, 42, 137, 137, 137};
	    int[] rl = {3, 2, 5, 50,50 , 2};
	    RunLengthEncoding rle7 = new RunLengthEncoding(4, 28, rr, rg, rb, rl);
	    PixImage o1 = rle7.toPixImage();
	    System.out.print(o1);
	  
	  
	  
	  
	  
	  
	  
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 ,9,12},
                                                   { 1, 4, 7 ,10,13},
                                                   { 2, 5, 8 ,11,14},
                                                   { 77,88,99,100,101}});

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
  
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
   // System.out.println("rle1.getWidth()=="+rle1.getWidth()+"rle1.getHeight()=="+rle1.getHeight());
    doTest(rle1.getWidth() == 4 && rle1.getHeight() == 5,
          "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
        //  System.out.println("tostring"+ rle1.toString());//000000000000000000000000000000000000000000000000000000000
         //  System.out.println(""+rle1.toPixImage());//000000000000000000000000000000000000000000000000000000000000000
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
   // System.out.println("tostring"+ rle1.toString());//0000000000000000000000000000000000000000000000000000000000000000
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
            //System.out.println("tostring"+ rle1.toString());//0000000000000000000000000000000000000000000000000000000000
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
           // System.out.println("tostring"+ rle1.toString()); //000000000000000000000000000000000000000000000000000000000000
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
                    //   System.out.println("tostring"+ rle1.toString());//0000000000000000000000000000000000000000000000000000000000000000
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
                     //  System.out.println("tostring"+ rle1.toString());//0000000000000000000000000000000000000000000000000000000000000000
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    System.out.println("tostring"+ rle2.toString());
    System.out.println(""+rle2.toPixImage());
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    System.out.println("tostring"+ rle2.toString());//0000000000000000000000000000000000000000000000000000000000000000
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    System.out.println("tostring"+ rle2.toString());//0000000000000000000000000000000000000000000000000000000000000000
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
  }
}
