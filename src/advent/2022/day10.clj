(ns advent.2022.day10
  (:require [clojure.string :as str]))


(defn parse [s]
  (str/split-lines s))

(defn parse-instruction [s]
  (let [v  (str/split s #" ")]
    [(get v 0) (Integer/parseInt (get v 1 "0"))]))

(defn perform-instruction [s v]
  (let [prev (last v)
        [i c] (parse-instruction s)]
    (case i
      "addx" (conj v prev (+ prev c))
      "noop" (conj v prev))))

(defn cpu [instructions]
  (reduce (fn [a i]
            (perform-instruction i a))
          [1]
          instructions))

(defn solution1 [ins]
  (->> (parse ins)
       cpu
       (#(for [i '(20 60 100 140 180 220) ]
           ;; "during cycle" means before completion
           (* i (% (dec i)))))
       (reduce +)))

(solution1 (slurp "input/2022/day10.txt"))
;; 12840
;;
;; Part II
;;

(defn crt [pos X]
  (let [tick (inc pos)]
    (if (and (>= tick X)
             (< tick (+ X 3)))
      "#"
      ".")))

(defn solution2 [s]
  (->> (parse s)
       cpu
       (partition 40)
       (map #(map-indexed crt %))
       (map #(apply str %))
       (interpose "\n")
       (apply str)))

(println
 (solution2 (slurp "input/2022/day10.txt")))
