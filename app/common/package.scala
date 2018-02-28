import collection.mutable
import annotation.tailrec

package object common {
  abstract class PrimesMap extends mutable.SortedMap[Int, Seq[Int]] {
    override def update(k: Int, v: Seq[Int]) = {
      // map memory optimization
      @tailrec
      def loop(sm: mutable.SortedMap[Int, Seq[Int]]): Seq[Int] =
        if (sm.isEmpty) v
        else {
          val current = sm.head._2
          if (current.size > v.size) v
          else if (current.size == v.size) current
          else loop(sm.tail)
        }
			
      super.update(k, loop(this))
    }
  }
	
  object PrimesMap {
    def empty: mutable.SortedMap[Int, Seq[Int]] = mutable.SortedMap.empty[Int, Seq[Int]]
  }
}