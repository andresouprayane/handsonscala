object BinarySearch {

  /**
   * Performs a binary search on a sorted sequence to find the target element.
   *
   * @param sorted the sorted sequence to search in
   * @param target the element to search for
   * @tparam T the type of elements in the sequence, must have an Ordering
   * @return true if the target is found, false otherwise
   */
  def binarySearch[T: Ordering](sorted: IndexedSeq[T], target: T): Boolean = {
    def binarySearch0(start: Int, end: Int): Boolean = {
      // If the sequence you are trying to search has no items, the item cannot be found
      if (start == end) false
      else {
        val middle = (start + end) / 2
        // Otherwise, take the item at the middle of the sequence
        val middleItem = sorted(middle)
        // and compare it to the item you are looking for
        val comparison = Ordering[T].compare(target, middleItem)
        // If the item is what you are looking for, you have found it
        if (comparison == 0) true
        // Otherwise, if the item is greater than the one you are looking for,
        // binary search the left half of the sequence
        else if (comparison < 0) binarySearch0(start, middle)
        // If it is less than the item you are looking for, binary search the right half
        else binarySearch0(middle + 1, end)
      }
    }
    binarySearch0(0, sorted.length)
  }

  /**
   * Performs a binary search on a sorted sequence to find the index of the target element.
   *
   * @param sorted the sorted sequence to search in
   * @param target the element to search for
   * @tparam T the type of elements in the sequence, must have an Ordering
   * @return Some(index) if the target is found, None otherwise
   */
  def binarySearchIndex[T: Ordering](sorted: IndexedSeq[T], target: T): Option[Int] = {
    def binarySearch0(start: Int, end: Int): Option[Int] = {
      if (start == end) None
      else {
        val middle = (start + end) / 2
        val middleItem = sorted(middle)
        val comparison = Ordering[T].compare(target, middleItem)
        if (comparison == 0) Some(middle)
        else if (comparison < 0) binarySearch0(start, middle)
        else binarySearch0(middle + 1, end)
      }
    }
    binarySearch0(0, sorted.length)
  }

}