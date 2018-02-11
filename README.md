## VolleyPlus
Using faster and easier than Volley

## Screenshots

![Screenshot](https://s1.gifyu.com/images/volleyplus-v1.0.4.gif)

## Getting started

##### Dependency

    dependencies {
        compile 'ir.siaray:volleyplus:1.0.4'
    }

## Usage

##### To send json object request.

            JsonObjectRequest.getInstance(context, url)
                    .setTag(tag)
                    .setParams(params)
                    .setHeader(header)
                    .setMethod(method)
                    .setTimeout(timeoutMs)
                    .setNumberOfRetries(numOfRetry)
                    .setBackoffMultiplier(backoffMultiplier)
                    .setPriority(priority)
                    .setListener(listener, errorListener)
                    .send();

##### To send json array request.

            JsonArrayRequest.getInstance(context, url)
                    .setTag(tag)
                    .setParams(params)
                    .setHeader(header)
                    .setMethod(method)
                    .setTimeout(timeoutMs)
                    .setNumberOfRetries(numOfRetry)
                    .setBackoffMultiplier(backoffMultiplier)
                    .setPriority(priority)
                    .setListener(listener, errorListener)
                    .send();

##### To send string request.

            StringRequest.getInstance(context, url)
                    .setTag(tag)
                    .setParams(params)
                    .setHeader(header)
                    .setMethod(method)
                    .setTimeout(timeoutMs)
                    .setNumberOfRetries(numOfRetry)
                    .setBackoffMultiplier(backoffMultiplier)
                    .setPriority(priority)
                    .setListener(listener, errorListener)
                    .send();

## License

    Copyright 2017 Siamak Rayeji

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
