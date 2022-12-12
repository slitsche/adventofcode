(ns advent.2022.day8-test
  (:require [advent.2022.day8 :as sut]
            [clojure.test :refer :all]))


(deftest parse
  (testing "map of trees"
    (is (= [[1 2 3]] (sut/parse "123")))
    (is (= [[1 2 3] [4 5 6]] (sut/parse "123
456")))))

(def testgrid
"30373
25512
65332
33549
35390")

(deftest visible
  (testing "count tree"
    (is (= 21 (sut/visible-trees (sut/parse testgrid))))))

(deftest treevisible
  (testing "one tree on the edge"
    (let [grid (sut/parse testgrid)]
      (is (= true (sut/treevisible? [0 0] grid)))
      (is (= true (sut/treevisible? [1 4] grid)))
      (is (= true (sut/treevisible? [2 0] grid)))
      (is (= true (sut/treevisible? [4 4] grid))))))

(def grid (sut/parse testgrid))

(deftest treevisible2
  (testing "inner trees visible"
    (is (= true (sut/treevisible? [1 1] grid)))
    (is (= true (sut/treevisible? [2 1] grid)))))


(deftest neighbours
  (testing "getting all neighbours"
    (is (= [[1 0]] (sut/neighbours [1 1] :left grid)))
    (is (= [[0 1]] (sut/neighbours [1 1] :up grid)))
    (is (= [[2 1] [3 1] [4 1]] (sut/neighbours [1 1] :down grid)))
    (is (= [[1 2] [1 3] [1 4]] (sut/neighbours [1 1] :right  grid)))
    ;; we expect the order from inner most to outer most for scenic-score
    (is (= [[1 2] [0 2]] (sut/neighbours [2 2] :up grid)))
    (is (= [[3 3] [3 4]] (sut/neighbours [3 2] :right grid)))))


(deftest scenic-score
  (testing "calc score for one tree"
    (is (= 4 (sut/scenic-score [1 2] grid)))
    (is (= 8 (sut/scenic-score [3 2] grid)))))

(deftest scenic-score-dir
  (testing "special case?"
    (is (= 2
           (sut/scenic-score-dir [3 2] :right grid)))))
