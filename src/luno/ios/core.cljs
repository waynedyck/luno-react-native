(ns luno.ios.core
  (:require [clojure.string :refer [blank?]]
            [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [luno.handlers]
            [luno.subs]
            [luno.db :as db]
            [luno.shared.ui :as ui]
            [luno.ios.ui :as ios-ui]
            [luno.ios.styles :as s]
            [luno.ios.scenes.root :refer [root-scene]]))

(defn app-root []
  [ios-ui/navigator
   {:initial-route {:title                 "Luno"
                    :component             (r/reactify-component root-scene)
                    :right-button-title    "Add city"
                    :on-right-button-press (fn [_]
                                             (ios-ui/show-dialog {:text     "Please, input city's name"
                                                                  :callback (fn [city]
                                                                              (dispatch [:load-weather city]))}))}
    :style         (get-in s/styles [:app])}])

(defn init []
  (dispatch-sync [:initialize-schema])
  (dispatch [:load-from-db :city])
  (.registerComponent ui/app-registry "luno" #(r/reactify-component app-root)))
