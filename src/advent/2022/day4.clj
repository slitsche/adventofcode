(ns advent.2022.day4
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(defn split-input [s]
  (->>
   (str/split s #"-")
   (map #(Integer/parseInt %))
   (vec)))

(defn overlap-incl [t1 t2]
  (and (s/int-in-range? (get t1 0)
                        (inc (get t1 1))
                        (get t2 0))
       (s/int-in-range? (get t1 0)
                        (inc (get t1 1))
                        (get t2 1))))

(defn overlap [r1 r2]
  (let [t1 (split-input r1)
        t2 (split-input r2)]
    (or (overlap-incl t1 t2)
        (overlap-incl t2 t1))))

(def assignments
  (->> (slurp "input/2022/day4.txt")
       (str/split-lines)
       (map #(str/split % #","))))

;; part 1
(->> (map #(apply overlap %) assignments)
     (filter true?)
     (count))

(defn overlap-part [t1 t2]
  (or (s/int-in-range? (get t1 0)
                        (inc (get t1 1))
                        (get t2 0))
       (s/int-in-range? (get t1 0)
                        (inc (get t1 1))
                        (get t2 1))))

(defn overlap2 [r1 r2]
  (let [t1 (split-input r1)
        t2 (split-input r2)]
    (or (overlap-part t1 t2)
        (overlap-part t2 t1))))

;; part 2
(->> (map #(apply overlap2 %) assignments)
     (filter true?)
     (count))
