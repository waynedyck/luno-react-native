(ns luno.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [reg-sub-raw]]))

(reg-sub-raw
  :get-android-drawer
  (fn [db _]
    (reaction
      (get-in @db [:android :drawer]))))

(reg-sub-raw
  :get-shared-tab
  (fn [db _]
    (reaction
      (get-in @db [:shared :tab]))))

(reg-sub-raw
  :get-data
  (fn [db _]
    (reaction
      (get @db :data))))
