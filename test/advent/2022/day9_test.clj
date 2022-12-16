(ns advent.2022.day9-test
  (:require  [clojure.test :refer :all]
             [advent.2022.day9 :as sut]))

(def testdata
"R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2")

(deftest parse
  (testing "translate in seq of motions"
    (is (= [:right :right :right] (sut/parse "R 3")))
    (is (= [:right :right :up :up] (sut/parse "R 2
U 2")))
    ))

(deftest parse2
  (testing "testdata has 24 motions"
    (is (= 24 (count (sut/parse testdata))))))

(deftest motion
  (testing "different motions"
    (is (= [[2 1] [1 0]] (sut/motion :up [[2 0] [1 0]])))
    (is (= [[2 2] [2 1]] (sut/motion :up [[2 1] [2 0]])))
    (is (= [[2 3] [2 2]] (sut/motion :up [[2 2] [1 1]])))
    (is (= [[3 1] [2 2]] (sut/motion :down [[3 2] [2 2]])))
    (is (= [[-2 -1] [-2 0]] (sut/motion :down [[-2 0] [-3 1]])))
    (is (= [[3 2] [2 2]] (sut/motion :right [[2 2] [1 1]])))
    (is (= [[1 1] [1 0]] (sut/motion :left [[2 1] [1 0]])))
    ))

(deftest vdiff
  (testing "vector differenz"
    (is (= [1 2] (sut/vdiff [2 3] [1 1])))))

(deftest vadd
  (testing "adding vectors"
    (is (= [3 3] (sut/vadd [2 2] [1 1])))))

(deftest new-direction
  (testing "calc diff to new point"
    (is (= [1 1] (sut/new-dir [2 1])))
    (is (= [1 -1] (sut/new-dir [1 -2])))
    (is (= [-1 -1] (sut/new-dir [-2 -1])))
    (is (= [1 0] (sut/new-dir [2 0])))))



(deftest counting
  (testing "count testdata"
    (is (= 13 (sut/solution1 testdata)))))


;; solution 2

(deftest rope-motion
  (testing "5 knots"
    (let [before [[4 2] [3 1] [2 1] [1 1] [1 1]]
          after  [[4 3] [4 2] [3 2] [2 2] [1 1]]]
      (is (= after (sut/rope-motion :up before))))))

(def example2
"R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20")

(deftest example
  (testing "solution for example2"
    (is (= 36 (sut/solution2 example2)))))
