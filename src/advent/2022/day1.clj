(ns advent.2022.day1
  (:require [clojure.string :as str]))

(defn get-elfs [s]
  (->>
   (str/split-lines s )
   (partition-by #(= % ""))
   (filter #(not= % '("")))
   (map #(map (fn [x] (Integer/parseInt x)) %))))

(defn sumup [elf]
  (apply + elf))

(defn most-calories [elfs]
  (reduce
   (fn [a i] (let [s (sumup i)]
               (if (> a s)
                 a
                 s)))
   0
   elfs))

(def input (slurp "input/2022/day1.txt"))

(def allelfs (get-elfs input))

(most-calories allelfs)

;; part two find the three top most elfs


(defn lowest [top]
  (get top 0 0))

(defn top-three [top elf]
  (->> (conj top (sumup elf))
       (apply sorted-set)
       (vec)
       ((fn [v] (subvec v (if (> (count v) 3) 1 0))))))

(defn top-three-calories [elfs]
  (reduce
   (fn [a i] (let [s (sumup i)]
               (top-three a i)))
   []
   elfs))

(println
 (apply +
        (top-three-calories allelfs)))
