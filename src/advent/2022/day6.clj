(ns advent.2022.day6)


(defn kmp [input idx buffer]
  (let [c (get input idx)
        buf  (conj buffer c)]
    (if (= (count buf) (count (set buf)))
      buf
      (subvec buf 1))))

(defn search [input len]
  (let [chars (char-array input)]
    (loop [i 0
           start []]
      (if (= len (count start))
        i ;; should be 1 based index
        (recur (inc i) (kmp chars i start))))))

(def input (slurp "input/2022/day6.txt"))

(search input 14)
