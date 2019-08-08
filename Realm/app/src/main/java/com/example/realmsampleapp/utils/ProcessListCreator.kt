package com.example.realmsampleapp.utils

import com.example.realmsampleapp.models.ProcessList
import com.squareup.moshi.Moshi

class ProcessListCreator {
    private val moshi = Moshi.Builder().build()
    private val processListJsonAdapter = moshi.adapter(ProcessList::class.java)

    private val processJson = """
        {
        "processList" :[
            {
                 "Id":84971,
                 "EnvironmentId":32281,
                 "Name":"Process One",
                 "Key":"523e3608-5f18-43d2-9df6-42c395589871",
                 "InputArguments":"{\"BoolArgument\":true,\"ObjectArgument\":{}}",
                 "Arguments":{
                    "Input":"[{\"name\":\"IntArgument\",\"type\":\"System.Int32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"StringArgument\",\"type\":\"System.String, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"BoolArgument\",\"type\":\"System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"LongArgument\",\"type\":\"System.Int64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ShortArgument\",\"type\":\"System.Int16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ByteArgument\",\"type\":\"System.Byte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"SByteArgument\",\"type\":\"System.SByte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UIntArgument\",\"type\":\"System.UInt32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UShortArgument\",\"type\":\"System.UInt16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ULongArgument\",\"type\":\"System.UInt64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"CharArgument\",\"type\":\"System.Char, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"DoubleArgument\",\"type\":\"System.Double, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"FloatArgument\",\"type\":\"System.Single, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false}]",
                    "Output":null
                 }
              },
              {
                 "Id":84972,
                 "EnvironmentId":32282,
                 "Name":"Process Two",
                 "Key":"523e3608-5f18-43d2-9df6-42c395589872",
                 "InputArguments":"{\"BoolArgument\":true,\"ObjectArgument\":{}}",
                 "Arguments":{
                    "Input":"[{\"name\":\"IntArgument\",\"type\":\"System.Int32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"StringArgument\",\"type\":\"System.String, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"BoolArgument\",\"type\":\"System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"LongArgument\",\"type\":\"System.Int64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ShortArgument\",\"type\":\"System.Int16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ByteArgument\",\"type\":\"System.Byte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"SByteArgument\",\"type\":\"System.SByte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UIntArgument\",\"type\":\"System.UInt32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UShortArgument\",\"type\":\"System.UInt16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ULongArgument\",\"type\":\"System.UInt64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"CharArgument\",\"type\":\"System.Char, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"DoubleArgument\",\"type\":\"System.Double, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"FloatArgument\",\"type\":\"System.Single, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false}]",
                    "Output":null
                 }
              },
              {
                 "Id":84973,
                 "EnvironmentId":32283,
                 "Name":"Process Three",
                 "Key":"523e3608-5f18-43d2-9df6-42c395589873",
                 "InputArguments":"{\"BoolArgument\":true,\"ObjectArgument\":{}}",
                 "Arguments":{
                    "Input":"[{\"name\":\"IntArgument\",\"type\":\"System.Int32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"StringArgument\",\"type\":\"System.String, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"BoolArgument\",\"type\":\"System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"LongArgument\",\"type\":\"System.Int64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ShortArgument\",\"type\":\"System.Int16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ByteArgument\",\"type\":\"System.Byte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"SByteArgument\",\"type\":\"System.SByte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UIntArgument\",\"type\":\"System.UInt32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UShortArgument\",\"type\":\"System.UInt16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ULongArgument\",\"type\":\"System.UInt64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"CharArgument\",\"type\":\"System.Char, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"DoubleArgument\",\"type\":\"System.Double, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"FloatArgument\",\"type\":\"System.Single, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false}]",
                    "Output":null
                 }
              },
              {
                 "Id":84974,
                 "EnvironmentId":32284,
                 "Name":"Process Four",
                 "Key":"523e3608-5f18-43d2-9df6-42c395589874",
                 "InputArguments":"{\"BoolArgument\":true,\"ObjectArgument\":{}}",
                 "Arguments":{
                    "Input":"[{\"name\":\"IntArgument\",\"type\":\"System.Int32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"StringArgument\",\"type\":\"System.String, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"BoolArgument\",\"type\":\"System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"LongArgument\",\"type\":\"System.Int64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ShortArgument\",\"type\":\"System.Int16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ByteArgument\",\"type\":\"System.Byte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"SByteArgument\",\"type\":\"System.SByte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UIntArgument\",\"type\":\"System.UInt32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UShortArgument\",\"type\":\"System.UInt16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ULongArgument\",\"type\":\"System.UInt64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"CharArgument\",\"type\":\"System.Char, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"DoubleArgument\",\"type\":\"System.Double, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"FloatArgument\",\"type\":\"System.Single, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false}]",
                    "Output":null
                 }
              },
              {
                 "Id":84975,
                 "EnvironmentId":32285,
                 "Name":"Process Five",
                 "Key":"523e3608-5f18-43d2-9df6-42c395589875",
                 "InputArguments":"{\"BoolArgument\":true,\"ObjectArgument\":{}}",
                 "Arguments":{
                    "Input":"[{\"name\":\"IntArgument\",\"type\":\"System.Int32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"StringArgument\",\"type\":\"System.String, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"BoolArgument\",\"type\":\"System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"LongArgument\",\"type\":\"System.Int64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ShortArgument\",\"type\":\"System.Int16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ByteArgument\",\"type\":\"System.Byte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"SByteArgument\",\"type\":\"System.SByte, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UIntArgument\",\"type\":\"System.UInt32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"UShortArgument\",\"type\":\"System.UInt16, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"ULongArgument\",\"type\":\"System.UInt64, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"CharArgument\",\"type\":\"System.Char, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"DoubleArgument\",\"type\":\"System.Double, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false},
                    {\"name\":\"FloatArgument\",\"type\":\"System.Single, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\"required\":false,\"hasDefault\":false}]",
                    "Output":null
                 }
              }
        ]
    }"""

    private val processList: ProcessList? = processListJsonAdapter.fromJson(processJson)
    fun processList() = processList?.processList ?: emptyList()
}