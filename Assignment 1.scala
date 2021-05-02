
object assignment1{
  //Exercise 1
  /*Merge and Reduce function; uses tail recursion*/
  def mergeReduce(a: List[Int],b: List[Int],f:(Int,Int)=>Int,g:(Int,Int)=>Int): Int={
    //merge function that takes two lists and merges them into one
    def merge(l: List[Int],m: List[Int],acc: List[Int]=Nil):List[Int]={
      if(l.isEmpty || m.isEmpty) acc //if both lists are empty return the accumulator
      else merge(l.tail,m.tail,f(l.head,m.head)::acc) //else merge the two lists using the function f:(Int,Int)=>Int
    }
    //reduce function
    def reduce(n: List[Int]):Int= n match{
      case Nil=> -1 //if list is empty return -1
      case i::Nil=>i //if it has only one element return the head of list
      case a::b::c=>reduce(g(a,b)::c) //else reduce the list based on the second function g:(Int,Int)=>Int
    }
    //tail recursive call of the functions merge and reduce
    reduce(merge(a,b))
  }
  //test if the function is working
  val a1 = List(3, 7, 2, 9)
  val b1 = List(1, 8, 4, 6)
  val prod = (x: Int, y: Int) => x * y
  val sum = (x: Int, y: Int) => x + y
  mergeReduce(a1, b1, prod, sum) //121
  mergeReduce(a1,b1,_.min(_),_.max(_)) //7

  //Exercise 2
  /*Inspired from Programming with Scala, Martin Odersky*/
  /*Transpose function is generic*/
  /*The first for loop flattens list1 and puts it into one list
  starting with the first element of the list up to the last*/
  /*The second loop maps the elements of list1 with that of i */
  def transpose[A](list1:List[List[A]]):List[List[A]]={
    for(i<-List.range(0,list1(0).length))yield
      (for(ys<-list1)yield ys(i))
  }
  //test the function
  type Row=List[Int]
  type Matrix=List[Row]
  val m: Matrix = List(1 :: 2 :: Nil, 3 :: 4 :: Nil, 5 :: 6 :: Nil)
  val mt=transpose(m)
  m.map(_.mkString("")).foreach(println)
  mt.map(_.mkString("")).foreach(println)
  transpose(List(List(27, 97, 28), List(76, 85, 35), List(24, 70, 8)))

  //Exercise 3
  /*Inspired from Programming with Scala, Martin Odersky*/
  /*The dotProduct function takes two lists and multiplies them together then sums them up */
  def dotProduct(l :List[Int],m: List[Int]): Int={
    if(l.isEmpty && m.isEmpty)0
    else l.head*m.head+dotProduct(l.tail,m.tail)
  }

  /*The matrixMultiply takes two lists*/
  /*The first for loop flattens the first list xs*/
  /*The second loop flattens the second list after it is transposed
  * then yields the product using the dotProduct function*/
  def matrixMultiply(list1: List[List[Int]],list2: List[List[Int]]):List[List[Int]]={
    //For matrix multiplication one matrix must be transposed
    //The val transposedList stores the transposed ys list
    val transposedList=transpose(list2)
    for(xs<-list1)yield
      for(y<-transposedList)yield
        dotProduct(xs,y)
  }
  //test the function
  type Row5=List[Int]
  type Matrix5=List[Row5]
  val u: Matrix5 = List(1 :: 2 :: 3 :: Nil, 4 :: 5 :: 6 :: Nil)
  val q: Matrix5 = List(7 :: 8 :: Nil, 9 :: 10 :: Nil, 11 :: 12 :: Nil)
  val ab: Matrix5 = matrixMultiply(u, q)
  ab.map(_.mkString(" ")).foreach(println)
  matrixMultiply(List(List(1, 0, 6), List(5, 7, 4), List(6, 0, 7)), List(List(2, 5, 5), List(3, 7, 3), List(4, 7, 8)))
}