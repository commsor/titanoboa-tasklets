

![alt Logo](https://s3.eu-central-1.amazonaws.com/www.titanoboa.io/tb-logo-dark-nosubtitle.svg)
# Titanoboa Step Functions
This repository contains sample ready-made steps for [titanoboa](https://titanoboa.io) (github repository is [here](https://github.com/mikub/titanoboa) ).

## PDF
[![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/pdf.svg)](https://clojars.org/io.titanoboa.tasklet/pdf)

Generates a pdf file based on job properties. Primarily uses [clj-pdf](https://github.com/clj-pdf/clj-pdf) library. Refer to the library's documentation for detailed information on the generation process and all supported properties.

### :workload-fn
```clojure
io.titanoboa.tasklet.pdf/generate-pdf
```
### Sample Properties
```clojure
{:pdf-sections [{}
   [:list {:roman true}
          [:chunk {:style :bold} "a bold item"]
          "another item"
          "yet another item"]
   [:phrase "some text"]
   [:phrase "some more text"]
   [:paragraph "yet more text"]] 
 :file-name "example.pdf" 
 :pdf-metadata {:bottom-margin 10, :creator "Jane Doe", :doc-header ["inspired by" "William Shakespeare"], :right-margin 50, :left-margin 10, :footer "page", :header "page header", :size "a4", :title "Test doc", :author "John Doe", :top-margin 20, :subject "Some subject"}}
```
### Sample Step Definition
```clojure
{:type :pdf-generation
 :supertype :tasklet
 :properties
 {:pdf-sections [{}
   [:list {:roman true}
          [:chunk {:style :bold} "a bold item"]
          "another item"
          "yet another item"]
   [:phrase "some text"]
   [:phrase "some more text"]
   [:paragraph "yet more text"]] 
 :file-name "example.pdf" 
 :pdf-metadata {:bottom-margin 10, :creator "Jane Doe", :doc-header ["inspired by" "William Shakespeare"], :right-margin 50, :left-margin 10, :footer "page", :header "page header", :size "a4", :title "Test doc", :author "John Doe", :top-margin 20, :subject "Some subject"}}
 :workload-fn #titanoboa.exp.Expression{:value "io.titanoboa.tasklet.pdf/generate-pdf", :type "clojure"}}
```
