

![alt Logo](https://s3.eu-central-1.amazonaws.com/www.titanoboa.io/tb-logo-dark-nosubtitle.svg)
# Titanoboa Step Functions
This repository contains sample ready-made steps for [titanoboa](https://titanoboa.io) (github repository is [here](https://github.com/mikub/titanoboa) ):

AWS <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws.svg"/>

* [AWS EC2](#aws-ec2-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-ec2.svg"/>

* [AWS S3](#aws-s3-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-s3.svg"/> 

* [AWS SES](#aws-ses-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-ses.svg"/> 

* [AWS SNS](#aws-sns-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-sns.svg"/> 

* [AWS SQS](#aws-sqs-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-sqs.svg"/> 

[Http Client](#http-client-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/http-client.svg"/>

[Smtp Client](#smtp-client-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/smtp.svg"/>

[SFTP Client](#ssh-and-sftp-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/custom.svg"/>

[SSH Client](#ssh-and-sftp-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/cluster.svg"/>

[PDF Generation](#pdf-) <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/pdf-generation.svg"/>


---

## AWS EC2 <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-ec2.svg"/>

Provides functions to list, start and stop EC2 instances. Primarily uses [amazonica](https://github.com/mcohen01/amazonica) library. Refer to the library's documentation for detailed information on the supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/aws-ec2.svg)](https://clojars.org/io.titanoboa.tasklet/aws-ec2)
 2. Require namespace: `io.titanoboa.tasklet.aws.ec2`

### Usage
#### List EC2 Instances
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.ec2/list-instances
```
#### Sample Step Definition
```clojure
{:type :aws-ec2-list,
 :supertype :tasklet,
 :description "Lists all EC2 instances for all reservations.\nReturns :ec2-instances key with list of instances as a value:\n{:ec2-instances [{instance1 map} {instance2 map} ...]}",
 :properties {:credentials {:access-key "", :secret-key "", :endpoint "eu-central-1"}},
 :workload-fn #titanoboa.exp.Expression{:value "io.titanoboa.tasklet.aws.ec2/list-instances", :type "clojure"}}
 ```
 ---
 #### Start EC2 Instances
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.ec2/start-instances
```
#### Sample Step Definition
```clojure
{:type :aws-ec2-start,
 :supertype :tasklet,
 :description "Starts an EC2 instance.\nReturns :starting-instances key with status value map.",
 :workload-fn #titanoboa.exp.Expression{:value "io.titanoboa.tasklet.aws.ec2/start-instances", :type "clojure"}
 :properties {:credentials {:access-key "", :secret-key "", :endpoint "eu-central-1"}, :instance-ids ["i-0a123a454b678aeb6"]}
}
```
 ---
 #### Stop EC2 Instances
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.ec2/stop-instances
```
#### Sample Step Definition
```clojure
{:type :aws-ec2-stop,
 :supertype :tasklet,
 :description "Stops an EC2 instance.\nReturns :stopping-instances key with status value map.",
 :properties {:credentials {:access-key "", :secret-key "", :endpoint "eu-central-1"}, :instance-ids ["i-0a123a454b678aeb6"]},
 :workload-fn #titanoboa.exp.Expression{:value "io.titanoboa.tasklet.aws.ec2/stop-instances", :type "clojure"}
}
```
---
---
## AWS S3 <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-s3.svg"/>

Provides functions to read, download and upload S3 objects. Primarily uses [amazonica](https://github.com/mcohen01/amazonica) library. Refer to the library's documentation for detailed information on the supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/aws-s3.svg)](https://clojars.org/io.titanoboa.tasklet/aws-s3)
 2. Require namespace: `io.titanoboa.tasklet.aws.s3`

### Usage
#### Read S3 Object
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.s3/read
```
#### Sample Step Definition
```clojure
{:type :aws-s3-read,
 :supertype :tasklet,
 :description "Reads textual content of a s3 file and returns it as a job property :s3-object",
 :workload-fn #titanoboa.exp.Expression{:value "io.titanoboa.tasklet.aws.s3/read", :type "clojure"}
 :properties {:key "index.html", :credentials {:access-key "", :secret-key "", :endpoint "eu-central-1"}, :bucket ""}}
 ```
 ---
 #### Download S3 Object
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.s3/download
```
#### Sample Step Definition
```clojure
{:type :aws-s3-download,
 :supertype :tasklet,
 :description "Downloads a file from s3 bucket to job directory under the specified name.",
 :properties {:key "index.html", :credentials {:access-key "", :secret-key "", :endpoint "eu-central-1"}, :save-as "path/to/file", :bucket "bucket-name"},
 :workload-fn #titanoboa.exp.Expression{:value "io.titanoboa.tasklet.aws.s3/download", :type "clojure"}}
```
 ---
 #### Upload S3 Object
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.s3/upload
```
#### Sample Step Definition
```clojure
{:type        :aws-s3-upload,
 :supertype   :tasklet,
 :description "Uploads specified file from job directory into the given s3 bucket.",
 :properties  {:key "index.bkp", :credentials {:access-key "", :secret-key "", :endpoint "eu-central-1"}, :file-path "index.html", :bucket ""},
 :workload-fn #titanoboa.exp.Expression{:value "io.titanoboa.tasklet.aws.s3/upload", :type "clojure"}}
```
---
---

## AWS SES <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-ses.svg"/>

Provides functions to send email via AWS SES. Primarily uses [amazonica](https://github.com/mcohen01/amazonica) library. Refer to the library's documentation for detailed information on the supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/aws-ses.svg)](https://clojars.org/io.titanoboa.tasklet/aws-ses)
 2. Require namespace: `io.titanoboa.tasklet.aws.ses`

### Usage
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.ses/send-email
```
#### Sample Step Definition
```clojure
{:type :aws-ses,
 :supertype :tasklet,
 :description "Sends an email via SES.\nReturns :message-id key with message id value.\n",
 :properties {:credentials {:access-key "", :secret-key "", :endpoint "eu-west-1"}, :from "info@titanoboa.io",
              :message {:body {:html "testing 1-2-3-4", :text "testing 1-2-3-4"}, :subject "greetings from titanoboa"}, :to ["miro@titanoboa.io"]},
 :workload-fn #titanoboa.exp.Expression{:value "io.titanoboa.tasklet.aws.ses/send-email", :type "clojure"}} 
 ```
 ---
 ---
 
 ## AWS SNS <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-sns.svg"/>

Provides functions to send notification via AWS SNS. Primarily uses [amazonica](https://github.com/mcohen01/amazonica) library. Refer to the library's documentation for detailed information on the supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/aws-sns.svg)](https://clojars.org/io.titanoboa.tasklet/aws-sns)
 2. Require namespace: `io.titanoboa.tasklet.aws.sns`

### Usage
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.sns/publish
```
#### Sample Step Definition
```clojure
{:type :aws-sns,
 :supertype :tasklet,
 :description "Publishes a message into an SNS topic.",
 :workload-fn #titanoboa.exp/Expression{:value "io.titanoboa.tasklet.aws.sns/publish",
                                        :type "clojure"},
 :properties  {:topic-arn "arn:aws:sns:us-east-1:676820690883:my-topic",
               :subject "test",
               :message "",
               :message-attributes {"attr" "value"}}}
 ```
 ---
 ---
 
 ## AWS SQS <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/aws-sqs.svg"/>

Provides functions to send message via AWS SQS. Primarily uses [amazonica](https://github.com/mcohen01/amazonica) library. Refer to the library's documentation for detailed information on the supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/aws-sqs.svg)](https://clojars.org/io.titanoboa.tasklet/aws-sqs)
 2. Require namespace: `io.titanoboa.tasklet.aws.sqs`

### Usage
#### :workload-fn
```clojure
io.titanoboa.tasklet.aws.sqs/send-message
```
#### Sample Step Definition
```clojure
{:type :aws-sqs,
 :supertype :tasklet,
 :description "Sends a text message to a queue.",
 :workload-fn #titanoboa.exp/Expression{:value "io.titanoboa.tasklet.aws.sqs/send-message",
                                        :type "clojure"},
 :properties {:credentials {:access-key "",
                            :secret-key "",
                            :endpoint "eu-central-1"},
              :message-attributes {},
              :message-body "",
              :queue-url ""}}
 ```
 
 ---
 ---
 
## Http Client <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/http-client.svg"/>

Makes an http(s) call and returns (parsed) response. Primarily uses [clj-http](https://github.com/dakrone/clj-http) library. Refer to the library's documentation for detailed information on the generation process and all supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/http-client.svg)](https://clojars.org/io.titanoboa.tasklet/http-client)
 2. Require namespace: `io.titanoboa.tasklet.httpclient`

### Usage
#### :workload-fn
```clojure
io.titanoboa.tasklet.httpclient/request
```
#### Sample Step Definition
```clojure
{:type :http-client
 :supertype :tasklet
 :workload-fn #titanoboa.exp/Expression{:value "io.titanoboa.tasklet.httpclient/request" :type "clojure"}
 :properties {:url "https://jsonplaceholder.typicode.com/posts/1"
              :request-method :get
              :as :json
              :proxy-host "127.0.0.1"
              :proxy-port 8118
              :response-property-name :rest-response
              :body-only? false
              :connection-pool {:timeout 5 :threads 4 :insecure? false :default-per-route 10}}}
              
 ```
 
 ---
 ---
 
 ## Smtp Client <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/smtp.svg"/>

Sends email via smtp. Primarily uses [postal](https://github.com/drewr/postal) library. Refer to the library's documentation for detailed information on the generation process and all supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/smtp.svg)](https://clojars.org/io.titanoboa.tasklet/smtp)
 2. Require namespace: `io.titanoboa.tasklet.smtp`

### Usage
#### :workload-fn
```clojure
titanoboa.tasklet.smtp/send
```
#### Sample Step Definition
```clojure
{:type :smtp
 :supertype :tasklet
 :workload-fn #titanoboa.exp/Expression{:value "titanoboa.tasklet.smtp/send"}
 :properties {:connection {:host "localhost"
                           :port 25
                           :user ""
                           :pass ""
                           :ssl false
                           :tls false}
              :email {:from "miro@example.bla"
                      :to "joe@example.com"
                      :cc ["joe@example.com", "jim@example.com", "jeff@example.com"]
                      :bcc "archive@example.com"
                      :subject "Cat!"
                      :date #titanoboa.exp/Expression{:value "(java.util.Date.)"}
                      :message-id ""
                      :user-agent ""
                      :body [{:type "text/plain"
                              :content "Hey folks,\n\nCheck out these pictures of my cat!"}
                             {:type :inline
                              :content #titanoboa.exp/Expression{:value "(File. \"/tmp/lester-flying-photoshop\")"}
                              :content-type "image/jpeg"
                              :file-name "lester-flying.jpeg"}
                             {:type :attachment
                              :content #titanoboa.exp/Expression{:value "(File. \"/tmp/lester-upside-down.jpeg\")"}}]}}}              
 ```
 
 ---
 ---
 
   ## SSH and SFTP <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/cluster.svg"/>

SSH and SFTP Client. Primarily uses [clj-ssh](https://github.com/hugoduncan/clj-ssh) library. Refer to the library's documentation for detailed information on the generation process and all supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/ssh.svg)](https://clojars.org/io.titanoboa.tasklet/ssh)
 2. Require namespace: `io.titanoboa.tasklet.ssh`

### Usage
#### SSH
#### :workload-fn
```clojure
io.titanoboa.tasklet.ssh/ssh
```
#### Sample Step Definition
```clojure
 ```

#### SFTP
#### :workload-fn
```clojure
io.titanoboa.tasklet.ssh/sftp
```
#### Sample Step Definition
```clojure
 ```

 ---
 ---
 
## PDF <img width="28" height="28" align="left" src="https://github.com/mikub/titanoboa-tasklets/blob/master/_doc/step-icons/pdf-generation.svg"/>

Generates a pdf file based on job properties. Primarily uses [clj-pdf](https://github.com/clj-pdf/clj-pdf) library. Refer to the library's documentation for detailed information on the generation process and all supported properties.

### Installation
 1. Add following maven coordinates into titanoboa's external dependencies file: [![Clojars Project](https://img.shields.io/clojars/v/io.titanoboa.tasklet/pdf.svg)](https://clojars.org/io.titanoboa.tasklet/pdf)
 2. Require namespace: `io.titanoboa.tasklet.pdf`

### Usage
#### :workload-fn
```clojure
io.titanoboa.tasklet.pdf/generate-pdf
```
#### Sample Properties
```clojure
{:pdf-sections [[:list {:roman true}
          [:chunk {:style :bold} "a bold item"]
          "another item"
          "yet another item"]
   [:phrase "some text"]
   [:phrase "some more text"]
   [:paragraph "yet more text"]] 
 :file-name "example.pdf" 
 :pdf-metadata {:bottom-margin 10, :creator "Jane Doe", :doc-header ["inspired by" "William Shakespeare"], :right-margin 50, :left-margin 10, :footer "page", :header "page header", :size "a4", :title "Test doc", :author "John Doe", :top-margin 20, :subject "Some subject"}}
```
#### Sample Step Definition
```clojure
{:type :pdf-generation
 :supertype :tasklet
 :properties
 {:pdf-sections [[:list {:roman true}
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
