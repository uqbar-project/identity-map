package org.uqbar.utils.collections.immutable

import org.scalatest.FreeSpec
import org.scalatest.Matchers

class IdentityMapTest extends FreeSpec with Matchers {

	case class Key()

	"IdentityMap" - {

		val key1 = Key()
		val key2 = Key()
		val key3 = Key()
		val map = IdentityMap(key1 -> 1, key2 -> 2)

		"size should reflect the map's size, based on identity" in {
			map should have size 2
		}

		"get" - {
			"should return Some(value), for a key identical to one present in the map" in {
				map.get(key1) should be(Some(1))
				map.get(key2) should be(Some(2))
			}
			"should return None, for a key not identical to one present in the map" in {
				map.get(key3) should be(None)
			}
		}

		"+" - {
			"should not change the original map" in {
				map + (key3 -> 3)

				map should have size 2
				map(key1) should be (1)
				map(key2) should be (2)
			}

			"if the given key is not identical to one present in the map, should return another map with an extra entry" in {
				val otherMap = map + (key3 -> 3)

				otherMap should have size 3
				otherMap(key1) should be (1)
				otherMap(key2) should be (2)
				otherMap(key3) should be (3)
			}

			"if the given key is identical to one present in the map, should return another map with that entry updated" in {
				val otherMap = map + (key2 -> 3)

				otherMap should have size 2
				otherMap(key1) should be (1)
				otherMap(key2) should be (3)
			}
		}

		"-" - {
			"should not change the original map" in {
				map - key1

				map should have size 2
				map(key1) should be (1)
				map(key2) should be (2)
			}

			"if the given key is not identical to one present in the map, should return the same map" in {
				val otherMap = map - key3

				otherMap should be theSameInstanceAs map
			}

			"if the given key is identical to one present in the map, should return another map without that entry" in {
				val otherMap = map - key2

				otherMap should have size 1
				otherMap(key1) should be (1)
			}
		}

		"iterator" - {
			"of empty map" - {
				"should not have next" in {
					IdentityMap().iterator.hasNext should be(false)
				}

				"should not iterate any entry pair" in {
					var count = 0
					IdentityMap[Key, Int]().iterator.foreach(_ => count += 1)

					count should be (0)
				}
			}
			"of non-empty map" - {
				"should have next" in {
					val iterator = map.iterator

					iterator.hasNext should be(true)

					iterator.next
					iterator.hasNext should be(true)

					iterator.next
					iterator.hasNext should be(false)
				}

				"should iterate every entry pair" in {
					var count = 0
					map.iterator.foreach(_ => count += 1)

					count should be (2)
				}
			}
		}

		"==" - {
			"two maps with identical entries should be equal" in {
				IdentityMap(key1 -> 1, key2 -> 2) should be (map)
			}
			"two maps with equal, but non-identical entries, should not be equal" in {
				IdentityMap(key1 -> 1, key3 -> 2) should not(be (map))
			}
		}

		"hashCode" - {
			"two maps with identical entries should have the same hashCode" in {
				IdentityMap(key1 -> 1, key2 -> 2).hashCode should be (map.hashCode)
			}
		}

		"transform operations return type should be also an IdentityMap" - {
			val mapedMap: IdentityMap[Key, Int] = map.map{ case (k, v) => (k, v + 1) }
			val filteredMap: IdentityMap[Key, Int] = map.filter{ case (_, v) => v > 1 }
			val droppedMap: IdentityMap[Key, Int] = map.drop(1)
		}
	}
}