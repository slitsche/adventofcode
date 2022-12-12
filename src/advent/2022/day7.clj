(ns advent.2022.day7
  (:require [clojure.string :as str]))

(defn get-dir [l]
  (-> (str/split l #" ")
      (last)))

(defn starts-with-digit? [s]
  (->> (get s 0)
      (int)
      (#(and (<= (int \0) %) (>= (int \9) %)))))

(defn parse-line [agg line]
;;  (println line)
  (cond
    (= line "$ cd ..")             (update agg :current pop)
    (str/starts-with? line "$ cd") (update agg :current conj (get-dir line))
    (str/starts-with? line "dir")  (assoc-in agg (conj (agg :current) (get-dir line))  {})
    (starts-with-digit? line)    (let [[ssize file] (str/split line #" ")
                                       size (Integer/parseInt ssize)]
                                   (assoc-in agg (conj (:current agg) file) size))
    :else   agg))

(defn parse [input]
  (->> (str/split-lines input)
       (reduce parse-line {:current []})
       (#(dissoc % :current))))

(defn sumtree [tree]
  (->>
   (tree-seq map? vals tree)
   (filter int?)
    (reduce +)))

(defn get-dirs [tree]
  (->>
   (tree-seq map? vals tree)
   (drop 1)  ;;  filter out the root nodel; this seems awkward
   (filter map?)))

(defn list-of-dirs [tree limit]
  (->> (get-dirs tree)
   (map sumtree)
   (filter #(>= limit %))
   (reduce +)))

(def input (slurp "input/2022/day7.txt"))

(defn part1-predicate [limit]
  (fn [n] (>= limit n)))

(-> (parse input)
    (list-of-dirs (part1-predicate 100000)))

;; part II

(def total 70000000)
(def needed 30000000)

(defn enough-for-update [used s]
  (let [free (- total used )]
    (> (+ s free) needed)))

(let [tree (parse input)
      used (sumtree tree)]
 (->>
   (get-dirs tree)
   (map sumtree)
   (filter (partial enough-for-update used))
   (sort <)
   (first)))
