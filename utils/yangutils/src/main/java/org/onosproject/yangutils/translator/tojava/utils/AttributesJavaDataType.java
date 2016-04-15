/*
 * Copyright 2016-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.yangutils.translator.tojava.utils;

import org.onosproject.yangutils.datamodel.YangDataTypes;
import org.onosproject.yangutils.datamodel.YangDerivedInfo;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangType;
import org.onosproject.yangutils.datamodel.YangTypeDef;
import org.onosproject.yangutils.translator.exception.TranslatorException;
import org.onosproject.yangutils.translator.tojava.HasJavaFileInfo;
import org.onosproject.yangutils.translator.tojava.JavaFileInfo;
import org.onosproject.yangutils.translator.tojava.javamodel.YangJavaTypeDef;

import static org.onosproject.yangutils.translator.tojava.utils.JavaIdentifierSyntax.getCamelCase;
import static org.onosproject.yangutils.translator.tojava.utils.JavaIdentifierSyntax.getCaptialCase;
import static org.onosproject.yangutils.utils.UtilConstants.BIG_INTEGER;
import static org.onosproject.yangutils.utils.UtilConstants.BOOLEAN_DATA_TYPE;
import static org.onosproject.yangutils.utils.UtilConstants.BOOLEAN_WRAPPER;
import static org.onosproject.yangutils.utils.UtilConstants.BYTE;
import static org.onosproject.yangutils.utils.UtilConstants.BYTE_WRAPPER;
import static org.onosproject.yangutils.utils.UtilConstants.INT;
import static org.onosproject.yangutils.utils.UtilConstants.INTEGER_WRAPPER;
import static org.onosproject.yangutils.utils.UtilConstants.JAVA_LANG;
import static org.onosproject.yangutils.utils.UtilConstants.JAVA_MATH;
import static org.onosproject.yangutils.utils.UtilConstants.LONG;
import static org.onosproject.yangutils.utils.UtilConstants.LONG_WRAPPER;
import static org.onosproject.yangutils.utils.UtilConstants.PERIOD;
import static org.onosproject.yangutils.utils.UtilConstants.SHORT;
import static org.onosproject.yangutils.utils.UtilConstants.SHORT_WRAPPER;
import static org.onosproject.yangutils.utils.UtilConstants.STRING_DATA_TYPE;

/**
 * Represents java data types info corresponding to YANG type.
 */
public final class AttributesJavaDataType {

    /**
     * Creates an instance of attribute java data type.
     */
    private AttributesJavaDataType() {
    }

    /**
     * Returns java type.
     *
     * @param yangType YANG type
     * @return java type
     */
    public static String getJavaDataType(YangType<?> yangType) {

        YangDataTypes type = yangType.getDataType();

        switch (type) {
            case INT8:
                return BYTE;
            case INT16:
                return SHORT;
            case INT32:
                return INT;
            case INT64:
                return LONG;
            case UINT8:
                return SHORT;
            case UINT16:
                return INT;
            case UINT32:
                return LONG;
            case UINT64:
                return BIG_INTEGER;
            case DECIMAL64:
                //TODO: DECIMAL64.
            case STRING:
                return STRING_DATA_TYPE;
            case BOOLEAN:
                return BOOLEAN_DATA_TYPE;
            default:
                throw new TranslatorException("given data type is not supported.");
        }
    }

    /**
     * Returns java import class.
     *
     * @param yangType YANG type
     * @param isListAttr if the attribute need to be a list
     * @return java import class
     */
    public static String getJavaImportClass(YangType<?> yangType, boolean isListAttr) {

        YangDataTypes type = yangType.getDataType();

        if (isListAttr) {
            switch (type) {
                case INT8:
                    return BYTE_WRAPPER;
                case INT16:
                    return SHORT_WRAPPER;
                case INT32:
                    return INTEGER_WRAPPER;
                case INT64:
                    return LONG_WRAPPER;
                case UINT8:
                    return SHORT_WRAPPER;
                case UINT16:
                    return INTEGER_WRAPPER;
                case UINT32:
                    return LONG_WRAPPER;
                case UINT64:
                    return BIG_INTEGER;
                case DECIMAL64:
                    //TODO: DECIMAL64.
                case STRING:
                    return STRING_DATA_TYPE;
                case BOOLEAN:
                    return BOOLEAN_WRAPPER;
                case ENUMERATION:
                    //TODO: ENUMERATION.
                case BITS:
                    //TODO:BITS
                case BINARY:
                    //TODO:BINARY
                case LEAFREF:
                    //TODO:LEAFREF
                case IDENTITYREF:
                    //TODO:IDENTITYREF
                case EMPTY:
                    return BOOLEAN_WRAPPER;
                case UNION:
                    //TODO:UNION
                case INSTANCE_IDENTIFIER:
                    //TODO:INSTANCE_IDENTIFIER
                case DERIVED:
                    return getCaptialCase(getCamelCase(yangType.getDataTypeName(), null));
                default:
                    throw new TranslatorException("given data type is not supported.");
            }
        } else {
            switch (type) {
                case UINT64:
                    return BIG_INTEGER;
                case DECIMAL64:
                    //TODO: DECIMAL64.
                case STRING:
                    return STRING_DATA_TYPE;
                case ENUMERATION:
                    //TODO: ENUMERATION.
                case BITS:
                    //TODO:BITS
                case BINARY:
                    //TODO:BINARY
                case LEAFREF:
                    //TODO:LEAFREF
                case IDENTITYREF:
                    //TODO:IDENTITYREF
                case EMPTY:
                    //TODO:EMPTY
                case UNION:
                    //TODO:UNION
                case INSTANCE_IDENTIFIER:
                    //TODO:INSTANCE_IDENTIFIER
                case DERIVED:
                    return getCaptialCase(getCamelCase(yangType.getDataTypeName(), null));
                default:
                    return null;
            }
        }
    }

    /**
     * Returns java import package.
     *
     * @param yangType YANG type
     * @param isListAttr if the attribute is of list type
     * @param classInfo java import class info
     * @return java import package
     */
    public static String getJavaImportPackage(YangType<?> yangType, boolean isListAttr, String classInfo) {

        YangDataTypes type = yangType.getDataType();

        if (isListAttr) {
            switch (type) {
                case INT8:
                case INT16:
                case INT32:
                case INT64:
                case UINT8:
                case UINT16:
                case UINT32:
                case STRING:
                case BOOLEAN:
                    return JAVA_LANG;
                case UINT64:
                    return JAVA_MATH;
                case DECIMAL64:
                    //TODO: DECIMAL64.
                case ENUMERATION:
                    //TODO: ENUMERATION.
                case BITS:
                    //TODO:BITS
                case BINARY:
                    //TODO:BINARY
                case LEAFREF:
                    //TODO:LEAFREF
                case IDENTITYREF:
                    //TODO:IDENTITYREF
                case EMPTY:
                    //TODO:EMPTY
                case UNION:
                    //TODO:UNION
                case INSTANCE_IDENTIFIER:
                    //TODO:INSTANCE_IDENTIFIER
                case DERIVED:
                    return getTypDefsPackage(yangType);
                default:
                    throw new TranslatorException("given data type is not supported.");
            }
        } else {
            switch (type) {
                case UINT64:
                    //TODO: BIGINTEGER.
                case DECIMAL64:
                    //TODO: DECIMAL64
                case STRING:
                    return JAVA_LANG;
                case ENUMERATION:
                    //TODO: ENUMERATION.
                case BITS:
                    //TODO:BITS
                case BINARY:
                    //TODO:BINARY
                case LEAFREF:
                    //TODO:LEAFREF
                case IDENTITYREF:
                    //TODO:IDENTITYREF
                case EMPTY:
                    //TODO:EMPTY
                case UNION:
                    //TODO:UNION
                case INSTANCE_IDENTIFIER:
                    //TODO:INSTANCE_IDENTIFIER
                case DERIVED:
                    return getTypDefsPackage(yangType);
                default:
                    return null;
            }
        }
    }

    /**
     * Returns java package for typedef node.
     *
     * @param type YANG type
     * @return java package for typedef node
     */
    private static String getTypDefsPackage(YangType<?> type) {
        Object var = type.getDataTypeExtendedInfo();
        if (!(var instanceof YangDerivedInfo)) {
            throw new TranslatorException("type should have been derived.");
        }

        if (!(((YangDerivedInfo<?>) var).getReferredTypeDef() instanceof YangTypeDef)) {
            throw new TranslatorException("derived info is not an instance of typedef.");
        }

        YangJavaTypeDef typedef = (YangJavaTypeDef) ((YangDerivedInfo<?>) var).getReferredTypeDef();
        if (typedef.getJavaFileInfo().getPackage() == null) {
            return getPackageFromParent(typedef.getParent());
        }
        return typedef.getJavaFileInfo().getPackage();
    }

    /**
     * Returns package from parent node.
     *
     * @param parent parent YANG node
     * @return java package from parent node
     */
    private static String getPackageFromParent(YangNode parent) {
        if (!(parent instanceof HasJavaFileInfo)) {
            throw new TranslatorException("Invalid child node is being processed.");
        }
        JavaFileInfo parentInfo = ((HasJavaFileInfo) parent).getJavaFileInfo();
        return parentInfo.getPackage() + PERIOD + parentInfo.getJavaName().toLowerCase();
    }
}
