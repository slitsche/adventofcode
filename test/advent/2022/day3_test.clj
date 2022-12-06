(ns advent.2022.day3-test
  (:require [advent.2022.day3 :as sut]
            [clojure.test :refer :all]
            [clojure.set :as set]))


(deftest wrong-item
  (testing "find overlap character"
    (is (= \b (sut/wrong-item "abvb")))
    (is (= \p (sut/wrong-item "vJrwpWtwJgWrhcsFMMfFFhFp")))
    (is (= \L (sut/wrong-item "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")))
    (is (= \P (sut/wrong-item "PmmdzqPrVvPwwTWBwg")))))


(deftest priorities
  (testing "get priority"
    (is (= 1  (sut/priority \a)))
    (is (= 27 (sut/priority \A)))
    (is (= 16 (sut/priority \p)))))


(deftest rucksack
  (testing "prio"
    (is (= 16 (sut/rucksack-prio "vJrwpWtwJgWrhcsFMMfFFhFp")))))

;; part 2

(deftest badge
  (testing "find-badge"
    (is (= \a (sut/find-badge ["abc" "gha"]))))
  (testing "prio team"
    (is (= 52 (sut/team-prio [
"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
"ttgJtRGJQctTZtZT"
"CrZsJsPPZsGzwwsLwLmpwMDw"])))))
