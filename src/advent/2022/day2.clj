(ns advent.2022.day2
  (:require [clojure.string :as str]))

(def adversary-me-score {
          :rock {:scissors 0
                 :rock     3
                 :paper    6}
          :paper {:paper   3
                  :scissors 6
                  :rock    0}
          :scissors {:scissors 3
                     :rock     6
                     :paper    0}
          })

(def shapescore
  {:rock 1
   :paper 2
   :scissors 3})

(defn score [adversary me]
  (let [my-shape (me shapescore)
        outcome (get-in adversary-me-score [adversary me])]

    (+ my-shape outcome)))

(def decode
  {"A" :rock
   "B" :paper
   "C" :scissors
   "X" :rock
   "Y" :paper
   "Z" :scissors})

(defn parse-line [s]
  (map decode  (str/split s #" ")))


(def guide (slurp "input.txt"))

(def games (map parse-line (str/split-lines guide)))

(def scores (map #(apply score %) games))

;; Part I
(apply + scores)

;;;  Part II

;; in order to apply the other meaning of X
;; we swap the map of scores

(defn swap-map [m]
  (reduce-kv (fn [m k v]
               (assoc m v k))
             {}
             m))

(defn reverse-table [m]
  (reduce-kv (fn [a k v]
               (assoc a k (swap-map v)))
             {}
             m))

(def shape-by-result (reverse-table adversary-me-score))

(def decode-2
  {"A" :rock
   "B" :paper
   "C" :scissors
   "X" 0
   "Y" 3
   "Z" 6})

(defn parse-line-2 [s]
  (map decode-2  (str/split s #" ")))

(get-in shape-by-result [:rock 3])

(def game-2 (map parse-line-2 (str/split-lines guide)))

(defn score-2 [adversary my-score]
  (let [shape (get-in shape-by-result [adversary my-score])]
    (+ my-score (shapescore shape))))

(def scores-2 (map #(apply score-2 %) game-2))

(apply + scores-2)
