package assignment

import assignment.Table


class DataFrame (private val data: Table) {
  /**
  * Returns a set of statistics (min, max, average and standard deviation) about
  * the given feature. See exercise 2.9 (Standard deviation).
  * Note: built-in functions map and reduce allowed here.
  *
  * @param i Feature/attribute index for which the statistics are to be computed.
  * @return An object of type Stats containing the statistics (see Stats.scala).
  */
  def stats(i: Int): Stats = ???

  /**
  * Returns a new DataFrame where the values have been standarized.
  * See exercise 2.12 (Standarization).
  */
  def standarize(): DataFrame = ???

  /**
  * Returns the distance/dissimilarity matrix for the current data.
  * See exercises 4.4 (Minkowski) and 6.5 (Dissimilarity matrix).
  * @param measure The distance measure. It should accept "euclidean" (default)
  * and "manhattan".
  * @return A Table object (List[List[Double]]) where each row represents a the
  * distance between data point A and all other data points.
  */
  def distMatrix(measure: String = "euclidean"): Table = ???

  /**
  * Returns a histogram for the fiven feature.
  * Note: List built-in functions allowed here.
  *
  * @param i Feature/attribute index for which the histogram is computed.
  * @param bins Number of bins (splits) into which the data is counted.
  * @return A list of tuples, where the first element of the tuple represents
  * the lower boundary of the bin, and the second the counts for elements equal
  * or aboved that boundary and lower than the next.
  *
  */
  def histogram(i: Int, bins: Int): List[(Double, Int)] = ???

  /**
  * Displays the first 10 rows in console.
  */
  def show(): Unit = data.take(10).map(_.mkString("\t| ")).foreach(println)

  /**
  * Displays a simple plot for the histrogram in console.
  * @param i Feature/attribute index for which the histogram is to be computed.
  * @param bins Number of bins (splits) into which the data is counted.
  */
  def plotHistogram(i: Int, bins: Int): Unit =
    histogram(i, bins).map{case (i, c) =>
      "> %2.2f ".format(i) +
      (1 to c).map(_ => "-").mkString("|", "", "o")
    }.foreach(println)

}

/**
* This companion object will be the entry point. Users may load datasets using
* the read function, e.g.:
*   val df: DataFrame = DataFrame.read("path/to/file/data.csv")
*/
object DataFrame {
  /**
  * Loads and returns a DataFrame for the given data file. It should accept CSV
  * (comma separated value) files with numeric data only.
  * Note: Built-in functions allowed here. Check scala.io.Source for reading files.
  *
  * @param path Full path to data file.
  * @param sep (Optional) The separation character between columns in the file.
  * @return An Either object where the Left represents a DataFrame (when
  * loaded correctly), and the Right a String (an error message in case the
  * loading failed, e.g., no numeric values).
  */
  val df = Source.fromFile("src\\main\\resources\\iris.csv").getLines.toList.map(_.trim)
  def read(path: String, sep: String = ","): Either[DataFrame, String] = ???
    
}
