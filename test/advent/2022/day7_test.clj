(ns advent.2022.day7-test
  (:require  [clojure.test :refer :all]
             [advent.2022.day7 :as sut]))

(def testdata
"$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k")

(def t1 "$ cd /
$ ls
dir a
148 b.txt
850 c.dat
dir d")

(deftest parsing
  (testing "directory"
    (is (= {"/" {"a" {} "d" {} "b.txt" 148 "c.dat" 850}} (sut/parse t1)))))

(def t2 (str t1 "  ;; this newline is important :)
$ cd a
$ ls
234 e.txt
$ cd ..
$ cd d
$ ls
345 f.wer"
           ))

(deftest parse2
  (testing "step out of dir"
    (is (= {"/" {"a" {"e.txt" 234} "d" {"f.wer" 345} "b.txt" 148 "c.dat" 850}}
           (sut/parse t2)))))

(require '[clojure.pprint :refer :all])

(-> (sut/parse t2)
    (pprint))

(deftest sum
  (testing "t1 sum"
    (is (= 998 (sut/sumtree (sut/parse t1))))
    (is (= 1577 (sut/sumtree (sut/parse t2))))))

(deftest sum-dirs
  (testing "get sum of dirs small than x"
    (is (= 579
           (sut/list-of-dirs (sut/parse t2) 400)))
    (is (= 95437
           (sut/list-of-dirs (sut/parse testdata) 100000)))))

(def outermosed 48381165)
(deftest enough
  (testing "predicate fn"
    (is (= true (sut/enough-for-update outermosed 24933642)))))


;; (sut/list-of-dirs (sut/parse testdata) )
(let [tree (sut/parse testdata)
      used (sut/sumtree tree)]
 (->>
   (sut/get-dirs tree)
   (map sut/sumtree)
   (filter (partial sut/enough-for-update used))
   (sort <)
   (first)))
