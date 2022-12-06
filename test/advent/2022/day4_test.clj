(ns advent.2022.day4-test
  (:require [advent.2022.day4 :as sut]
            [clojure.test :refer :all]))


(deftest overlap
  (testing "input range overlap fully"
    (is (= true (sut/overlap "4-6" "6-6")))
    (is (= true (sut/overlap "6-6" "4-6")))
    (is (= true (sut/overlap "2-8" "3-7")))
    (is (= false  (sut/overlap "5-7" "7-9")))))

(deftest overlap2
  (testing "partial overlap"
    (is (= true (sut/overlap2 "5-7" "7-9")))
    (is (= true (sut/overlap2 "7-9" "5-7")))
    (is (= true (sut/overlap2 "4-6" "6-6")))
    (is (= true (sut/overlap2 "6-6" "4-6")))
    (is (= true (sut/overlap2 "4-6" "4-4")))
    (is (= true (sut/overlap2 "4-8" "2-6")))
    (is (= true (sut/overlap2 "2-6" "4-8")))
    (is (= true (sut/overlap2 "2-8" "3-7")))
    (is (= true (sut/overlap2 "3-7" "2-8")))
    (is (= false (sut/overlap2 "2-4" "6-8")))
    ))
