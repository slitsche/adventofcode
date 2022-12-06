(ns advent.2022.day5
  (:require [clojure.string :as str]))

;; [H]                 [Z]         [J]
;; [L]     [W] [B]     [G]         [R]
;; [R]     [G] [S]     [J] [H]     [Q]
;; [F]     [N] [T] [J] [P] [R]     [F]
;; [B]     [C] [M] [R] [Q] [F] [G] [P]
;; [C] [D] [F] [D] [D] [D] [T] [M] [G]
;; [J] [C] [J] [J] [C] [L] [Z] [V] [B]
;; [M] [Z] [H] [P] [N] [W] [P] [L] [C]
;;  1   2   3   4   5   6   7   8   9


(def s1 (vec (reverse '("H" "L" "R" "F" "B" "C" "J" "M"))))
(def s2 (vec (reverse '("D" "C" "Z"))))
(def s3 (vec (reverse '("W" "G" "N" "C" "F" "J" "H"))))
(def s4 (vec (reverse '("B" "S" "T" "M" "D" "J" "P"))))
(def s5 (vec (reverse '("J" "R" "D" "C" "N"))))
(def s6 (vec (reverse '("Z" "G" "J" "P" "Q" "D" "L" "W"))))
(def s7 (vec (reverse '("H" "R" "F" "T" "Z" "P"))))
(def s8 (vec (reverse '("G" "M" "V" "L"))))
(def s9 (vec (reverse '("J" "R" "Q" "F" "P" "G" "B" "C"))))

(def stacks [s1 s2 s3 s4 s5 s6 s7 s8 s9])

(defn move [[c from to] stacks]
  (let [f (dec from)
        t (dec to)]
    (loop [cnt c
           stk stacks]
      (if (= cnt 0)
        stk
        (recur (dec cnt) (single f t stk))))))

(defn single [i j s]
  (let [crate (peek (get s i))]
    (-> (update s i pop)
        (update j conj crate))))


(def moves (drop 10 (str/split-lines (slurp "input/2022/day5.txt"))))

(defn get-args [s]
  (let [v (str/split s #" ")]
    (->> [(get v 1)
          (get v 3)
          (get v 5)]
         (map #(Integer/parseInt %))
         (vec))))

;; Part I
(defn solution [f]
  (->> (map get-args moves)
       (reduce (fn [a i] (f i a))
               stacks
               )
       (map peek)
       (apply str)
       (println)))

(solution move)

(defn move9001 [[c from to] stacks]
  (let [f (dec from)
        t (dec to)
        fstck (get stacks f)
        split (- (count fstck) c)
        tomove (subvec fstck split)]
    (-> (update stacks f #(subvec % 0 split))
        (update t #(into [] (concat % tomove))))))

(solution move9001)
