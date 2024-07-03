curl -m 310 -X POST https://us-central1-bullla-one-d-apps-cn-92c3.cloudfunctions.net/func-publisher-java \
-H "Authorization: bearer $(gcloud auth print-identity-token)" \
-H "Content-Type: application/json" \
-d '{
  "name": "Hello World"
}'