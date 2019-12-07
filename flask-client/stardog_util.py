import stardog

conn_details = {
    'endpoint': 'http://45.55.41.230:5820',
    'username': 'admin',
    'password': 'admin'
}

def getTerms(inputTerm):
    with stardog.Admin(**conn_details) as admin:
        with stardog.Connection('rtdm', **conn_details) as conn:
            results = conn.select("PREFIX : <http://resourcedescription.tut.fi/ontology/processTaxonomyModel#>\n" +
                                  "select ?superclass where { \n" +
                                  ":" + inputTerm + " rdfs:subClassOf* ?superclass\n" +
                                  "}")

            result_list = []
            for result in results['results']['bindings']:
                tempOut = result[next(iter(result))]['value']
                tempOut = tempOut.split("#")[1]

                if tempOut != 'ProcessTaxonomyElement':
                    result_list.append(tempOut)

            return result_list


# print(getTerms("Bagging"))