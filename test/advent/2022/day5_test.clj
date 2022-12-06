(ns advent.2022.day5-test
  (:require [advent.2022.day5 :as sut]
            [clojure.test :refer :all]))

(deftest move
  (testing "move one crate"
    (let [stacks [[:a :b] [:c :d]]]
      (is (= [[:a :b :d] [:c]] (sut/move [1 2 1] stacks)))))
  (testing "move three crates"
    (let [stacks [[:a] [:x :y :z]]]
      (is (= [[:a :z :y :x] []] (sut/move [3 2 1] stacks))))))


(deftest move9001
  (testing "multiple move"
    (let [stacks [[:a :b] [:c :d]]]
      (is (= [[:a :b :c :d] []] (sut/move9001 [2 2 1] stacks))))))
