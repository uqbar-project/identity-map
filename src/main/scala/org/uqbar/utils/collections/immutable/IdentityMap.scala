package org.uqbar.utils.collections.immutable

import java.util.IdentityHashMap

import scala.collection.generic.CanBuildFrom
import scala.collection.generic.MapFactory
import scala.collection.immutable.Map
import scala.collection.immutable.MapLike

object IdentityMap extends MapFactory[IdentityMap] {
	def empty[K, V] = new IdentityMap[K, V]

	implicit def canBuildFrom[K, V]: CanBuildFrom[Coll, (K, V), IdentityMap[K, V]] = new MapCanBuildFrom[K, V]
}

class IdentityMap[K, +V](inner: IdentityHashMap[K, V] = new IdentityHashMap[K, V]) extends Map[K, V] with MapLike[K, V, IdentityMap[K, V]] {

	override def empty = IdentityMap.empty[K, V]

	override def size = inner.size

	def get(key: K) = if (inner containsKey key) Some(inner get key) else None

	def iterator = new Iterator[(K, V)] {
		val innerIterator = inner.entrySet.iterator
		def hasNext = innerIterator.hasNext
		def next = {
			val nextEntry = innerIterator.next
			(nextEntry.getKey, nextEntry.getValue)
		}
	}

	def +[U >: V](entry: (K, U)) = {
		val nextInner = inner.clone.asInstanceOf[IdentityHashMap[K, U]]
		nextInner.put(entry._1, entry._2)
		new IdentityMap(nextInner)
	}

	def -(key: K) = if (!inner.containsKey(key)) this else {
		val nextInner = inner.clone.asInstanceOf[IdentityHashMap[K, V]]
		nextInner.remove(key)
		new IdentityMap(nextInner)
	}

}

