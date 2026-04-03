import java.lang.Math._

val items = Array(1,2,3,4,5,6,7,8,9)

var new_items = items
var min_indice = 0
var max_indice = items.length
var mid_indice = 0

mid_indice = ((max_indice - min_indice)/2).abs + min_indice
mid_indice

3 > items(mid_indice)




// def binary_search(searched_item: Int, items : Array[Int]): Boolean = {
//     // if (searched_item > items[Math.abs(items.length/2)]) items
//     var new_items = items
//     var min_indice = 0
//     var max_indice = items.length
//     var mid_indice = 0

//     while (min_indice != max_indice) {
//         mid_indice = ((max_indice - min_indice)/2).abs + min_indice
//         if (searched_item >= items(mid_indice)) {
//             min_indice = mid_indice
//         } else {
//             max_indice = mid_indice
//         }
        
//     }

//     if(items(min_indice) == searched_item)
//         true
//     else
//         false
// }

// binary_search(3,items)

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

binarySearch(Array(1, 3, 7, 9, 13), 3)









